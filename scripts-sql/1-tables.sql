SET search_path TO projet;
-- Schéma



DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet AUTHORIZATION projet;
GRANT ALL PRIVILEGES ON SCHEMA projet TO projet;




-- Tables



CREATE TABLE compte (

	IdCompte		INT			 	NOT NULL	GENERATED BY DEFAULT AS IDENTITY,
	Nom             VARCHAR (25)    NOT NULL,
	Prenom          VARCHAR (25)    NOT NULL,
	Pseudo			VARCHAR(25)		NOT NULL,
	MotDePasse		VARCHAR(25) 	NOT NULL,
	Email			VARCHAR(100)	NOT NULL,
	Credit          DECIMAL         NOT NULL  DEFAULT  0,
	PRIMARY KEY (IdCompte)
=======
    IdCompte        INT                 NOT NULL    GENERATED BY DEFAULT AS IDENTITY,
    Nom             VARCHAR (25)    NOT NULL,
    Prenom          VARCHAR (25)    NOT NULL,
    Pseudo            VARCHAR(25)        NOT NULL,
    MotDePasse        VARCHAR(25)     NOT NULL,
    Email            VARCHAR(100)    NOT NULL,
    Credit          DECIMAL         NOT NULL  DEFAULT  0,
    PRIMARY KEY (IdCompte)

);
CREATE UNIQUE INDEX idx_compte_pseudo ON compte (Pseudo ASC);



CREATE TABLE role (
    IdCompte        INT                NOT NULL,
    Role            VARCHAR(20)        NOT NULL,
    CHECK( Role IN ('ADMINISTRATEUR','UTILISATEUR') ),    
    FOREIGN KEY (IdCompte) REFERENCES compte (IdCompte),
    PRIMARY KEY (IdCompte, Role)
);



CREATE TABLE Produit (
    IdProduit      INT            NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
    NomProduit     VARCHAR(25)    NOT NULL ,
    Photo          VARCHAR(25)    NOT NULL,
    Description    VARCHAR(25)    NOT NULL,
    Prix           DECIMAL        NOT NULL DEFAULT 0,
    DateDebutEnchere Date         NOT NULL,
    DateFinEnchere   Date         NOT NULL,
    FlagVente      BOOLEAN        DEFAULT  FALSE,
    PRIMARY KEY (IdProduit)



);



CREATE TABLE enchere (
   IdEnchere   INT              NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
   IdProduit  INT               NOT NULL,
   IdCompte    INT              NOT NULL,
   PrixEnchere DECIMAL          NOT NULL,
   DateEnchere TIMESTAMP        NOT NULL,
   FOREIGN KEY (IdProduit) REFERENCES Produit (IdProduit),
   PRIMARY KEY (IdEnchere)
);



CREATE TABLE mouvement (
  IdMouvement  INT             NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
  Libelle      VARCHAR(50)     NOT NULL,
  DateMouvement TIMESTAMP      NOT NULL,
  PRIMARY KEY (IdMouvement)



);

CREATE TABLE produit ( 
    IdProduit      INT            NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
    NomProduit     VARCHAR(25)    NOT NULL ,
    Photo          VARCHAR(25)    NOT NULL,
    Description    VARCHAR(25)    NOT NULL,
    Prix           DECIMAL        NOT NULL DEFAULT 0,
    DateDebutEnchere Date         NOT NULL,
    DateFinEnchere   Date         NOT NULL,
    FlagVente      BOOLEAN        DEFAULT  FALSE,
    PRIMARY KEY (IdProduit)

);

CREATE TABLE enchere (
   IdEnchere   INT              NOT NULL  GENERATED BY DEFAULT AS IDENTITY,
   IdProduit  INT               NOT NULL,
   IdCompte    INT              NOT NULL,
   PrixEnchere DECIMAL          NOT NULL,
   DateEnchere TIMESTAMP        NOT NULL,
   FOREIGN KEY (IdProduit) REFERENCES produit (IdProduit),
   FOREIGN KEY (IdCompte) REFERENCES compte (IdCompte),
   PRIMARY KEY (IdEnchere)
);

CREATE TABLE mouvement (
  IdMouvement  INT             NOT NULL  GENERATED BY DEFAULT AS IDENTITY, 
  IdCompte     INT             NOT NULL,
  Libelle      VARCHAR(50)     NOT NULL,
  DateMouvement TIMESTAMP      NOT NULL,
  FOREIGN KEY (IdCOMPTE) REFERENCES compte (IdCompte),
  PRIMARY KEY (IdMouvement) 
  

);

