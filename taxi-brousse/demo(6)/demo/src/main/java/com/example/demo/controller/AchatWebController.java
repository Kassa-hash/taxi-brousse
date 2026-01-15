package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/achats")
public class AchatWebController {
    @Autowired
    private VoyageService voyageService;

    @Autowired
    private GareRoutiereService gareRoutiereService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AchatService achatService;

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private TypePaiementService typePaiementService;

    // Afficher la liste des achats
    @GetMapping
    public String afficherListeAchats(Model model) {
        List<Achat> achats = achatService.getAllAchats();
        Integer chiffreAffaires = achatService.calculateChiffreAffaires();
        List<Voyage> voyagesDisponibles = voyageService.getAllVoyages();
        
        model.addAttribute("achats", achats);
        model.addAttribute("chiffreAffaires", chiffreAffaires);
        model.addAttribute("voyagesDisponibles", voyagesDisponibles);
        return "achats/list";
    }

    // Page de recherche de voyage
    @GetMapping("/recherche")
    public String afficherRecherche(Model model) {
        List<GareRoutiere> gares = gareRoutiereService.getAllGareRoutiere();
        model.addAttribute("gares", gares);
        return "achats/recherche";
    }

    // Rechercher un voyage
    @PostMapping("/search-voyage")
    public String searchVoyage(
            @RequestParam Integer idGareDepart,
            @RequestParam Integer idGareArrivee,
            @RequestParam LocalDate datedepart,
            @RequestParam LocalTime heuredepart,
            @RequestParam Integer nbPlaces,
            Model model) {

        // Validation basique
        if (idGareDepart.equals(idGareArrivee)) {
            model.addAttribute("error", "La gare de départ et d'arrivée doivent être différentes");
            model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
            return "achats/recherche";
        }

        // DEBUG: Afficher les paramètres de recherche
        System.out.println("=== RECHERCHE DE VOYAGE ===");
        System.out.println("Gare départ ID: " + idGareDepart);
        System.out.println("Gare arrivée ID: " + idGareArrivee);
        System.out.println("Date: " + datedepart);
        System.out.println("Heure: " + heuredepart);
        System.out.println("Nombre de places: " + nbPlaces);

        // Rechercher les voyages disponibles
        List<Voyage> voyages = voyageService.getAllVoyages();
        Voyage voyageTrouve = null;

        System.out.println("Nombre total de voyages: " + voyages.size());

        for (Voyage v : voyages) {
            // DEBUG: Afficher chaque voyage
            System.out.println("\n--- Voyage ID: " + v.getIdVoyage() + " ---");
            System.out.println("Gare départ: " + v.getGareDepart().getIdGareRoutiere() + " - " + v.getGareDepart().getNom());
            System.out.println("Gare arrivée: " + v.getGareArrivee().getIdGareRoutiere() + " - " + v.getGareArrivee().getNom());
            System.out.println("Date départ: " + v.getDatedepart());
            System.out.println("Heure départ: " + v.getHeuredepart());
            System.out.println("Places disponibles: " + v.getNbPlaceDisponible());

            // Comparaisons individuelles pour déboguer
            boolean gareDepartMatch = v.getGareDepart().getIdGareRoutiere().equals(idGareDepart);
            boolean gareArriveeMatch = v.getGareArrivee().getIdGareRoutiere().equals(idGareArrivee);
            boolean dateMatch = v.getDatedepart().equals(datedepart);
            
            // Comparaison de l'heure avec truncation des secondes/nanosecondes
            LocalTime voyageHeure = v.getHeuredepart().withSecond(0).withNano(0);
            LocalTime rechercheHeure = heuredepart.withSecond(0).withNano(0);
            boolean heureMatch = voyageHeure.equals(rechercheHeure);

            System.out.println("Gare départ match: " + gareDepartMatch);
            System.out.println("Gare arrivée match: " + gareArriveeMatch);
            System.out.println("Date match: " + dateMatch);
            System.out.println("Heure match: " + heureMatch + " (Voyage: " + voyageHeure + " vs Recherche: " + rechercheHeure + ")");

            if (gareDepartMatch && gareArriveeMatch && dateMatch && heureMatch) {
                voyageTrouve = v;
                System.out.println("✓ VOYAGE TROUVÉ !");
                break;
            }
        }

        if (voyageTrouve == null) {
            System.out.println("✗ AUCUN VOYAGE TROUVÉ");
            model.addAttribute("error", "Aucun voyage disponible pour cette sélection");
            model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
            return "achats/recherche";
        }

        // Vérifier les places disponibles
        int placesDisponibles;
        try {
            placesDisponibles = Integer.parseInt(voyageTrouve.getNbPlaceDisponible());
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion des places disponibles: " + voyageTrouve.getNbPlaceDisponible());
            model.addAttribute("error", "Erreur dans les données du voyage");
            model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
            return "achats/recherche";
        }

        if (placesDisponibles < nbPlaces) {
            model.addAttribute("error", "Seulement " + placesDisponibles + " places disponibles pour ce voyage");
            model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
            return "achats/recherche";
        }

        // Passer à la page du formulaire client
        model.addAttribute("voyage", voyageTrouve);
        model.addAttribute("nbPlaces", nbPlaces);
        return "achats/client-form";
    }

    // Afficher la confirmation avec les informations du client
    @PostMapping("/confirmation")
    public String afficherConfirmation(
            @RequestParam String nomClient,
            @RequestParam(required = false) String telephoneClient,
            @RequestParam Integer idVoyage,
            @RequestParam Integer nbPlaces,
            Model model) {

        Optional<Voyage> voyage = voyageService.getVoyageById(idVoyage);
        if (!voyage.isPresent()) {
            return "redirect:/achats/recherche?error=Voyage introuvable";
        }

        // Vérifier à nouveau les places disponibles
        int placesDisponibles = Integer.parseInt(voyage.get().getNbPlaceDisponible());
        if (placesDisponibles < nbPlaces) {
            model.addAttribute("error", "Plus assez de places disponibles pour ce voyage");
            model.addAttribute("voyage", voyage.get());
            model.addAttribute("nbPlaces", nbPlaces);
            return "achats/client-form";
        }

        model.addAttribute("voyage", voyage.get());
        model.addAttribute("nbPlaces", nbPlaces);
        model.addAttribute("nomClient", nomClient);
        return "achats/confirmation";
    }

    // Traiter le paiement et créer l'achat
    @PostMapping("/payer")
    public String payer(
            @RequestParam String nomClient,
            @RequestParam Integer idVoyage,
            @RequestParam Integer nbPlaces,
            @RequestParam(required = false) String telephoneClient,
            @RequestParam String montant,
            Model model) {

        Optional<Voyage> voyage = voyageService.getVoyageById(idVoyage);
        if (!voyage.isPresent()) {
            return "redirect:/achats/recherche";
        }

        // Vérifier les places à nouveau
        int placesDisponibles = Integer.parseInt(voyage.get().getNbPlaceDisponible());
        if (placesDisponibles < nbPlaces) {
            model.addAttribute("error", "Plus assez de places disponibles");
            return "redirect:/achats/recherche";
        }

        try {
            // 1. Créer le client
            Client client = new Client();
            client.setNom(nomClient);
            Client clientSaved = clientService.createClient(client);
            
            // 2. Créer l'achat
            Achat achat = new Achat();
            achat.setNbplaces(nbPlaces);
            achat.setDate(LocalDate.now());
            achat.setVoyage(voyage.get());
            achat.setClient(clientSaved);
            Achat achatSaved = achatService.createAchat(achat);

            // 3. Mettre à jour les places disponibles du voyage
            int nouvellesPlaces = placesDisponibles - nbPlaces;
            voyage.get().setNbPlaceDisponible(String.valueOf(nouvellesPlaces));
            voyageService.updateVoyage(idVoyage, voyage.get());

            // 4. Créer le paiement
            Paiement paiement = new Paiement();
            paiement.setMontant(montant);
            paiement.setDate(LocalDate.now());
            paiement.setAchat(achatSaved);
            
            // Récupérer le type de paiement "En ligne" ou créer par défaut
            List<TypePaiement> typePaiements = typePaiementService.getAllTypePaiements();
            TypePaiement typePaypal = typePaiements.stream()
                    .filter(t -> t.getLibelle().equalsIgnoreCase("En ligne") || t.getLibelle().equalsIgnoreCase("Card"))
                    .findFirst()
                    .orElseGet(() -> {
                        TypePaiement tp = new TypePaiement();
                        tp.setLibelle("En ligne");
                        return typePaiementService.createTypePaiement(tp);
                    });
            
            paiement.setTypePaiement(typePaypal);
            Paiement paiementSaved = paiementService.createPaiement(paiement);

            // Passer les informations au template du ticket
            model.addAttribute("achat", achatSaved);
            model.addAttribute("paiement", paiementSaved);
            model.addAttribute("voyage", voyage.get());
            model.addAttribute("nbPlaces", nbPlaces);
            model.addAttribute("nomClient", nomClient);

            return "achats/ticket";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du traitement du paiement: " + e.getMessage());
            return "redirect:/achats/recherche";
        }
    }
}