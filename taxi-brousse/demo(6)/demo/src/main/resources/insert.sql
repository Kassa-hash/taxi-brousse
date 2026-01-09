-- Marque
INSERT INTO Marque(libelle) VALUES
('Toyota'),
('Nissan'),
('Hyundai');

-- Etat
INSERT INTO Etat(libelle) VALUES
('Disponible'),
('En panne'),
('En entretien');

-- Genre
INSERT INTO genre(libelle) VALUES
('Homme'),
('Femme');

-- Lieu
INSERT INTO Lieu(nom) VALUES
('Antananarivo'),
('Toamasina'),
('Mahajanga');

-- Statut
INSERT INTO Statut(libelle) VALUES
('Programmé'),
('En cours'),
('Terminé'),
('Annulé');

-- TypeVoyage
INSERT INTO type_voyage (libelle) VALUES
('National'),
('Régional');

-- TypePaiement
INSERT INTO type_paiement (libelle) VALUES
('Espèces'),
('Mobile Money'),
('Carte bancaire');

-- TypeClient
INSERT INTO typecllient  (libelle) VALUES
('Particulier'),
('Entreprise');

-- CategorieClient
INSERT INTO categorie_client(libelle) VALUES
('Standard'),
('VIP');

-- TypeMouvement
INSERT INTO type_mouvement(libelle) VALUES
('Entrée'),
('Sortie');

-- Caisse
INSERT INTO Caisse(libelle) VALUES
('Caisse principale');

-- TypeVoiture
INSERT INTO type_voiture(libelle) VALUES
('Minibus'),
('Bus');

-- Parametre (ex : prix km)
INSERT INTO Parametre(libelle, valeur) VALUES
('Prix_km', 500);

-- Utilisateur
INSERT INTO Utilisateur(login, mdp) VALUES
('admin', 'admin123');
