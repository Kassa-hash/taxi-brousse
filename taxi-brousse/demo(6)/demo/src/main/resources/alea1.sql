CREATE TABLE Voiture(
   Id_Voiture SERIAL,
   capacite INTEGER,
   PRIMARY KEY(Id_Voiture)
);

CREATE TABLE GareRoutiere(
   Id_GareRoutiere SERIAL,
   nom VARCHAR(50) ,
   PRIMARY KEY(Id_GareRoutiere)
);

CREATE TABLE Client(
   Id_Client SERIAL,
   nom VARCHAR(50) ,
   PRIMARY KEY(Id_Client)
);

CREATE TABLE TypePaiement(
   Id_TypePaiement SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_TypePaiement)
);

CREATE TABLE Voyage(
   Id_Voyage SERIAL,
   prix INTEGER,
   nbPlaceDisponible VARCHAR(50) ,
   datedepart DATE,
   heuredepart TIME,
   Id_GareRoutiere INTEGER NOT NULL,
   Id_GareRoutiere_1 INTEGER NOT NULL,
   PRIMARY KEY(Id_Voyage),
   FOREIGN KEY(Id_GareRoutiere) REFERENCES GareRoutiere(Id_GareRoutiere),
   FOREIGN KEY(Id_GareRoutiere_1) REFERENCES GareRoutiere(Id_GareRoutiere)
);

CREATE TABLE Achat(
   Id_Achat SERIAL,
   nbplaces INTEGER,
   date_ DATE,
   Id_Voyage INTEGER NOT NULL,
   Id_Client INTEGER NOT NULL,
   PRIMARY KEY(Id_Achat),
   FOREIGN KEY(Id_Voyage) REFERENCES Voyage(Id_Voyage),
   FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client)
);

CREATE TABLE Paiement(
   Id_Paiement SERIAL,
   montant VARCHAR(50) ,
   date_ DATE,
   Id_TypePaiement INTEGER NOT NULL,
   Id_Achat INTEGER NOT NULL,
   PRIMARY KEY(Id_Paiement),
   UNIQUE(Id_Achat),
   FOREIGN KEY(Id_TypePaiement) REFERENCES TypePaiement(Id_TypePaiement),
   FOREIGN KEY(Id_Achat) REFERENCES Achat(Id_Achat)
);


INSERT INTO Voiture (capacite) VALUES
(9),
(15),
(25);

INSERT INTO gare_routiere  (nom) VALUES
('Gare Analakely'),
('Gare Fasan ny Karana'),
('Gare Ambodivona');

INSERT INTO Client (nom) VALUES
('Rakoto'),
('Rabe'),
('Rasoa');

INSERT INTO type_paiement  (libelle) VALUES
('Espèces'),
('Mobile Money'),
('Carte bancaire');

INSERT INTO Voyage (prix, nb_place_disponible, datedepart, heuredepart, id_gare_routiere ,  id_gare_routiere_1) VALUES
(5000, '20', '2024-07-01', '08:00:00', 1, 2),
(7000, '15', '2024-07-02', '09:00:00', 2, 3),
(6000, '10', '2024-07-03', '10:00:00', 3, 1);

INSERT INTO Achat (
   nbplaces,
   date,
   Id_Voyage,
   Id_Client
) VALUES
(2, '2026-01-10', 1, 1),
(1, '2026-01-11', 2, 2),
(3, '2026-01-12', 3, 3);

INSERT INTO Paiement (
   montant,
   date,
    id_type_paiement,
   id_achat 
) VALUES
('10000', '2026-01-10', 1, 4),
('7000', '2026-01-11', 2, 5),
('18000', '2026-01-12', 3, 6);


INSERT INTO gare_routiere  (nom) VALUES
('Gare ambolomadinika Toamasina');

INSERT INTO Voyage (prix, nb_place_disponible, datedepart, heuredepart, id_gare_routiere ,  id_gare_routiere_1) VALUES
(5000, '20', '2026-01-14', '14:00:00', 2, 4);