package com.example.demo.controller;

import com.example.demo.entity.Achat;
import com.example.demo.entity.CategoriePlace;
import com.example.demo.service.AchatService;
import com.example.demo.service.ClientService;
import com.example.demo.service.VoyageService;
import com.example.demo.service.CategoriePlaceService;
import com.example.demo.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/achats")
public class AchatController {
    @Autowired
    private AchatService achatService;

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CategoriePlaceService categorieService;

    @Autowired
    private VoitureService voitureService;

    @GetMapping
    public ResponseEntity<List<Achat>> getAllAchats() {
        List<Achat> achats = achatService.getAllAchats();
        return ResponseEntity.ok(achats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAchatById(@PathVariable Integer id) {
        Optional<Achat> achat = achatService.getAchatById(id);
        if (achat.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("achat", achat.get());
            response.put("voyage", achat.get().getVoyage());
            response.put("client", achat.get().getClient());
            response.put("categorie", achat.get().getCategorie()); // Ajout de la catégorie
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Achat>> getAchatsByClient(@PathVariable Integer idClient) {
        List<Achat> achats = achatService.getAllAchats();
        achats.removeIf(a -> !a.getClient().getIdClient().equals(idClient));
        return ResponseEntity.ok(achats);
    }

    // Nouveau endpoint pour acheter avec catégorie
    @PostMapping("/avec-categorie")
    public ResponseEntity<Achat> createAchatAvecCategorie(
            @RequestBody Map<String, Object> achatData) {
        try {
            Integer idVoyage = (Integer) achatData.get("idVoyage");
            Integer idClient = (Integer) achatData.get("idClient");
            Integer idCategorie = (Integer) achatData.get("idCategorie");
            Integer nbPlaces = (Integer) achatData.get("nbPlaces");

            Achat createdAchat = achatService.createAchatAvecCategorie(
                    idVoyage, idClient, idCategorie, nbPlaces);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAchat);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Achat> createAchat(@RequestBody Achat achat) {
        Achat createdAchat = achatService.createAchat(achat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAchat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achat> updateAchat(@PathVariable Integer id, @RequestBody Achat achat) {
        Achat updatedAchat = achatService.updateAchat(id, achat);
        if (updatedAchat != null) {
            return ResponseEntity.ok(updatedAchat);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchat(@PathVariable Integer id) {
        achatService.deleteAchat(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour récupérer les prix par catégorie pour un voyage
    @GetMapping("/voyage/{idVoyage}/prix-categories")
    public ResponseEntity<Map<String, Double>> getPrixParCategoriePourVoyage(
            @PathVariable Integer idVoyage) {
        Map<String, Double> prixParCategorie = achatService.getPrixParCategoriePourVoyage(idVoyage);
        return ResponseEntity.ok(prixParCategorie);
    }

    // Endpoint pour calculer la valeur maximale d'une voiture pour un voyage
    @GetMapping("/valeur-maximale/voiture/{idVoiture}/voyage/{idVoyage}")
    public ResponseEntity<Map<String, Object>> calculerValeurMaximale(
            @PathVariable Integer idVoiture,
            @PathVariable Integer idVoyage) {
        try {
            Double valeurMaximale = achatService.calculerValeurMaximaleVoiture(idVoiture, idVoyage);
            Map<String, Object> response = new HashMap<>();
            response.put("idVoiture", idVoiture);
            response.put("idVoyage", idVoyage);
            response.put("valeurMaximale", valeurMaximale);
            response.put("devise", "MGA"); // ou votre devise
            
            // Ajouter les détails
            Map<String, Object> details = achatService.getDetailsValeurMaximale(idVoiture, idVoyage);
            response.put("details", details);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Nouveau endpoint pour récupérer les catégories disponibles pour un voyage
    @GetMapping("/voyage/{idVoyage}/categories-disponibles")
    public ResponseEntity<List<Categorie>> getCategoriesDisponiblesPourVoyage(
            @PathVariable Integer idVoyage) {
        List<Categorie> categories = achatService.getCategoriesDisponiblesPourVoyage(idVoyage);
        return ResponseEntity.ok(categories);
    }

    // Endpoint pour vérifier la disponibilité des places par catégorie
    @GetMapping("/voyage/{idVoyage}/categorie/{idCategorie}/disponibilite")
    public ResponseEntity<Map<String, Object>> verifierDisponibilite(
            @PathVariable Integer idVoyage,
            @PathVariable Integer idCategorie,
            @RequestParam(defaultValue = "1") Integer nbPlaces) {
        try {
            boolean disponible = achatService.verifierDisponibilitePlaces(
                    idVoyage, idCategorie, nbPlaces);
            int placesRestantes = achatService.getPlacesRestantesParCategorie(idVoyage, idCategorie);
            
            Map<String, Object> response = new HashMap<>();
            response.put("disponible", disponible);
            response.put("placesRestantes", placesRestantes);
            response.put("nbPlacesDemandees", nbPlaces);
            response.put("idVoyage", idVoyage);
            response.put("idCategorie", idCategorie);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}