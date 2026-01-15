INSERT INTO voiture(capacite) VALUES (3), (5), (2);

-- Places (autant de places que la capacité de chaque voiture)
-- Voiture 1 (3 places)
INSERT INTO place(numero, statut, id_client, id_voiture) VALUES
(1, false, NULL, 1),
(2, false, NULL, 1),
(3, false, NULL, 1);

-- Voiture 2 (5 places)
INSERT INTO place(numero, statut, id_client, id_voiture) VALUES
(1, false, NULL, 2),
(2, false, NULL, 2),
(3, false, NULL, 2),
(4, false, NULL, 2),
(5, false, NULL, 2);

-- Voiture 3 (2 places)
INSERT INTO place(numero, statut, id_client, id_voiture) VALUES
(1, false, NULL, 3),
(2, false, NULL, 3);

-- Gares routières
INSERT INTO gare_routiere(nom) VALUES ('Gare Centrale'), ('Gare Nord'), ('Gare Sud');

-- Clients
INSERT INTO client(nom) VALUES ('Alice'), ('Bob'), ('Charlie');

-- Types de paiement
INSERT INTO type_paiement(libelle) VALUES ('Carte'), ('Espèces'), ('Mobile Money');

-- Voyages
INSERT INTO voyage(prix, nb_place_disponible, datedepart, heuredepart, id_gare_routiere, id_gare_routiere_1) VALUES
(15000, 3, '2026-01-20', '08:00:00', 1, 2),
(20000, 5, '2026-01-21', '09:30:00', 2, 3),
(10000, 2, '2026-01-22', '07:45:00', 3, 1);

-- Achats
INSERT INTO achat(nbplaces, date, id_voyage, id_client) VALUES
(1, '2026-01-10', 1, 1),
(2, '2026-01-11', 2, 2);

-- Paiements
INSERT INTO paiement(montant, date, id_type_paiement , id_achat) VALUES
('15000', '2026-01-10', 1, 1),
('40000', '2026-01-11', 2, 2);

-- Catégories de places
INSERT INTO categorie_place(libelle) VALUES ('Economique'), ('Affaires'), ('VIP');

-- Tarifs par catégorie et voyage
INSERT INTO trajet_categorie_prix(id_voyage, id_cat_place, prix) VALUES
(1, 1, 15000),
(1, 2, 20000),
(2, 1, 18000),
(2, 2, 22000),
(3, 1, 10000),
(3, 3, 25000);