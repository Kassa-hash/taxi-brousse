-- Employe
INSERT INTO Employe(nom, datenaissance, dateembauche, Id_genre) VALUES
('Rakoto', '1990-05-12', '2020-01-01', 1),
('Rasoa', '1992-07-20', '2021-03-15', 2);

-- Client
INSERT INTO Client(nom, telephone,  categorie_client_id_categorie_client, type_client_id_type_client , genre_id_genre ) VALUES
('Jean', '0340000001', 1, 1, 1),
('Marie', '0340000002', 2, 1, 2);

-- Voiture
INSERT INTO Voiture(immatriculation, nbplace, noteetat, datearivee, Id_Etat, Id_Marque) VALUES
('1234-TAA', 16, 5, '2023-01-01', 1, 1);


-- Itinéraire
INSERT INTO Itinéraire(distance, duree, Id_Lieu, Id_Lieu_1) VALUES
(350, 8, 1, 2);

-- ModeleVoyage
INSERT INTO ModeleVoyage(Prix, Id_Itinéraire, Id_TypeVoyage) VALUES
(30000, 1, 1);


INSERT INTO Voyage(daty, heuredepart, Id_ModeleVoyage, Id_Voiture, Id_Employe, Id_Employe_1) VALUES
('2026-01-10', '06:00', 1, 1, 1, 2);


INSERT INTO Statut_Voyage(Id_Voyage, Id_Statut, daty) VALUES
(1, 1, CURRENT_DATE);


-- Reservation
INSERT INTO Reservation(Id_Voyage, Id_Client) VALUES
(1, 1);

-- Paiement
INSERT INTO Paiement(Id_Reservation, Id_TypePaiement) VALUES
(1, 1);


INSERT INTO Place(numero, statut, Id_Client) VALUES
(1, TRUE, 1),
(2, FALSE, NULL);


INSERT INTO Remboursement(montant) VALUES
(30000);

INSERT INTO Annulation(Id_Remboursement, Id_Reservation) VALUES
(1, 1);


INSERT INTO Entretient(Date_, motif) VALUES
('2025-12-01', 'Vidange');

INSERT INTO VoitureEntretient(Id_Voiture, Id_Entretient) VALUES
(1, 1);


INSERT INTO Voituretypevoyage(Id_Voiture, Id_TypeVoyage) VALUES
(1, 1);

INSERT INTO Mouvement(montant, date_, Id_TypeMouvement) VALUES
(100000, '2025-12-15', 1);