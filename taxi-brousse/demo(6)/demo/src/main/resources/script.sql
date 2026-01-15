CREATE TABLE Marque(
   Id_Marque SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_Marque)
);

CREATE TABLE Etat(
   Id_Etat SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_Etat)
);

CREATE TABLE genre(
   Id_genre SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_genre)
);

CREATE TABLE Lieu(
   Id_Lieu SERIAL,
   nom VARCHAR(50) ,
   PRIMARY KEY(Id_Lieu)
);

CREATE TABLE Statut(
   Id_Statut SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_Statut)
);

CREATE TABLE TypeVoyage(
   Id_TypeVoyage SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_TypeVoyage)
);

CREATE TABLE TypePaiement(
   Id_TypePaiement SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_TypePaiement)
);

CREATE TABLE Utilisateur(
   Id_Utilisateur SERIAL,
   login VARCHAR(50) ,
   mdp VARCHAR(50) ,
   PRIMARY KEY(Id_Utilisateur)
);

CREATE TABLE Parametre(
   Id_Parametre SERIAL,
   libelle VARCHAR(50) ,
   valeur DOUBLE PRECISION,
   PRIMARY KEY(Id_Parametre)
);

CREATE TABLE TypeCllient(
   Id_TypeCllient SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_TypeCllient)
);

CREATE TABLE TypeMouvement(
   Id_TypeMouvement SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_TypeMouvement)
);

CREATE TABLE Caisse(
   Id_Caisse SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_Caisse)
);

CREATE TABLE Entretient(
   Id_Entretient SERIAL,
   Date_ VARCHAR(50) ,
   motif VARCHAR(50) ,
   PRIMARY KEY(Id_Entretient)
);

CREATE TABLE CategorieClient(
   Id_CategorieClient SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_CategorieClient)
);

CREATE TABLE TypeVoiture(
   Id_TypeVoiture SERIAL,
   libelle VARCHAR(50) ,
   PRIMARY KEY(Id_TypeVoiture)
);

CREATE TABLE Remboursement(
   Id_Remboursement SERIAL,
   montant INTEGER,
   PRIMARY KEY(Id_Remboursement)
);

CREATE TABLE Voiture(
   Id_Voiture SERIAL,
   immatriculation VARCHAR(50) ,
   nbplace INTEGER,
   noteetat INTEGER,
   datearivee DATE,
   Id_Etat INTEGER NOT NULL,
   Id_Marque INTEGER NOT NULL,
   PRIMARY KEY(Id_Voiture),
   FOREIGN KEY(Id_Etat) REFERENCES Etat(Id_Etat),
   FOREIGN KEY(Id_Marque) REFERENCES Marque(Id_Marque)
);

CREATE TABLE Employe(
   Id_Employe SERIAL,
   nom VARCHAR(50) ,
   datenaissance DATE,
   dateembauche DATE,
   Id_genre INTEGER NOT NULL,
   PRIMARY KEY(Id_Employe),
   FOREIGN KEY(Id_genre) REFERENCES genre(Id_genre)
);

CREATE TABLE Itinéraire(
   Id_Itinéraire SERIAL,
   distance NUMERIC(15,2)  ,
   duree NUMERIC(15,2)  ,
   Id_Lieu INTEGER NOT NULL,
   Id_Lieu_1 INTEGER NOT NULL,
   PRIMARY KEY(Id_Itinéraire),
   FOREIGN KEY(Id_Lieu) REFERENCES Lieu(Id_Lieu),
   FOREIGN KEY(Id_Lieu_1) REFERENCES Lieu(Id_Lieu)
);

CREATE TABLE Client(
   Id_Client SERIAL,
   nom VARCHAR(50) ,
   telephone VARCHAR(50) ,
   Id_CategorieClient INTEGER NOT NULL,
   Id_TypeCllient INTEGER NOT NULL,
   Id_genre INTEGER NOT NULL,
   PRIMARY KEY(Id_Client),
   FOREIGN KEY(Id_CategorieClient) REFERENCES CategorieClient(Id_CategorieClient),
   FOREIGN KEY(Id_TypeCllient) REFERENCES TypeCllient(Id_TypeCllient),
   FOREIGN KEY(Id_genre) REFERENCES genre(Id_genre)
);




CREATE TABLE ModeleVoyage(
   Id_ModeleVoyage SERIAL,
   Prix INTEGER,
   Id_Itinéraire INTEGER NOT NULL,
   Id_TypeVoyage INTEGER NOT NULL,
   PRIMARY KEY(Id_ModeleVoyage),
   FOREIGN KEY(Id_Itinéraire) REFERENCES Itinéraire(Id_Itinéraire),
   FOREIGN KEY(Id_TypeVoyage) REFERENCES TypeVoyage(Id_TypeVoyage)
);

CREATE TABLE MouvementCaisse(
   Id_MouvementCaisse SERIAL,
   montant INTEGER,
   date_ DATE,
   motif VARCHAR(50) ,
   Id_TypeMouvement INTEGER NOT NULL,
   Id_Caisse INTEGER NOT NULL,
   PRIMARY KEY(Id_MouvementCaisse),
   FOREIGN KEY(Id_TypeMouvement) REFERENCES TypeMouvement(Id_TypeMouvement),
   FOREIGN KEY(Id_Caisse) REFERENCES Caisse(Id_Caisse)
);

CREATE TABLE Voyage(
   Id_Voyage SERIAL,
   daty DATE,
   heuredepart TIME,
   Id_ModeleVoyage INTEGER NOT NULL,
   Id_Voiture INTEGER NOT NULL,
   Id_Employe INTEGER NOT NULL,
   Id_Employe_1 INTEGER NOT NULL,
   PRIMARY KEY(Id_Voyage),
   FOREIGN KEY(Id_ModeleVoyage) REFERENCES ModeleVoyage(Id_ModeleVoyage),
   FOREIGN KEY(Id_Voiture) REFERENCES Voiture(Id_Voiture),
   FOREIGN KEY(Id_Employe) REFERENCES Employe(Id_Employe),
   FOREIGN KEY(Id_Employe_1) REFERENCES Employe(Id_Employe)
);

CREATE TABLE Reservation(
   Id_Reservation SERIAL,
   Id_Voyage INTEGER NOT NULL,
   Id_Client INTEGER NOT NULL,
   PRIMARY KEY(Id_Reservation),
   UNIQUE(Id_Voyage),
   FOREIGN KEY(Id_Voyage) REFERENCES Voyage(Id_Voyage),
   FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client)
);

CREATE TABLE Paiement(
   Id_Paiement SERIAL,
   Id_Reservation INTEGER NOT NULL,
   Id_TypePaiement INTEGER NOT NULL,
   PRIMARY KEY(Id_Paiement),
   UNIQUE(Id_Reservation),
   FOREIGN KEY(Id_Reservation) REFERENCES Reservation(Id_Reservation),
   FOREIGN KEY(Id_TypePaiement) REFERENCES TypePaiement(Id_TypePaiement)
);

CREATE TABLE Annulation(
   Id_Annulation SERIAL,
   Id_Remboursement INTEGER NOT NULL,
   Id_Reservation INTEGER NOT NULL,
   PRIMARY KEY(Id_Annulation),
   UNIQUE(Id_Remboursement),
   UNIQUE(Id_Reservation),
   FOREIGN KEY(Id_Remboursement) REFERENCES Remboursement(Id_Remboursement),
   FOREIGN KEY(Id_Reservation) REFERENCES Reservation(Id_Reservation)
);

CREATE TABLE Statut_Voyage(
   Id_Voyage INTEGER,
   Id_Statut INTEGER,
   daty DATE,
   PRIMARY KEY(Id_Voyage, Id_Statut),
   FOREIGN KEY(Id_Voyage) REFERENCES Voyage(Id_Voyage),
   FOREIGN KEY(Id_Statut) REFERENCES Statut(Id_Statut)
);

CREATE TABLE VoitureEntretient(
   Id_Voiture INTEGER,
   Id_Entretient INTEGER,
   PRIMARY KEY(Id_Voiture, Id_Entretient),
   FOREIGN KEY(Id_Voiture) REFERENCES Voiture(Id_Voiture),
   FOREIGN KEY(Id_Entretient) REFERENCES Entretient(Id_Entretient)
);

CREATE TABLE Voituretypevoyage(
   Id_Voiture INTEGER,
   Id_TypeVoyage INTEGER,
   PRIMARY KEY(Id_Voiture, Id_TypeVoyage),
   FOREIGN KEY(Id_Voiture) REFERENCES Voiture(Id_Voiture),
   FOREIGN KEY(Id_TypeVoyage) REFERENCES TypeVoyage(Id_TypeVoyage)
);
