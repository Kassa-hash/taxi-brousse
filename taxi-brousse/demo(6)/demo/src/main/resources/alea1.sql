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
