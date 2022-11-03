SET search_path TO projet;


-- Supprime toutes les données

DELETE FROM role;
DELETE FROM produit;
DELETE FROM compte;
DELETE FROM enchere;
DELETE FROM mouvement;

-- Insère les données

-- Compte

INSERT INTO compte (idcompte,nom,prenom, pseudo, motdepasse, email,roles,credit ) VALUES
( 1, 'geek','ngadjui','elisabeth', 'geek', 'geek@jfox.fr','ADMINISTRATEUR',1500 ),
( 2, 'chef','totoum','gaston', 'chef', 'chef@jfox.fr','ADMINISTRATEUR',1200 ),
( 3, 'job','toto' ,'tata','job', 'job@jfox.fr','UTILISATEUR',1300 );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;

--Role
INSERT INTO role (idcompte, role) VALUES
( 1, 'ADMINISTRATEUR' ),
( 2, 'UTILISATEUR' ),
( 3, 'UTILISATEUR' );

-- Produit
INSERT INTO produit (idproduit,idcompte,nomproduit,photo,description,prix,datedebutenchere,datefinenchere,flagvente) VALUES
( 1,3, 'Iphone ','fg','X011',700,'2022-10-28','2022-11-15',false ),
( 2,3, 'ACER','fr','10EME',1000,'2022-10-28','2022-11-15',false ),
( 3,3, 'Samsung','fc','10HGF Generation',1004,'2022-11-28','2022-12-15',false ),
( 4,3, 'Tekno','ffv','10MMVK Generation',1500,'2022-11-28','2022-12-15',true );
ALTER TABLE produit ALTER COLUMN idproduit RESTART WITH 5;

INSERT INTO enchere (idenchere,idproduit,idcompte,prixenchere,dateenchere) VALUES
(1,1,1,900,'2022-10-01 15:20:01'),
(2,2,3,1200,'2022-10-01 15:20:01');
ALTER TABLE enchere ALTER COLUMN idenchere RESTART WITH 3;

INSERT INTO mouvement (idmouvement,idcompte,libelle,montant,datemouvement,statut) VALUES
(1,3,'virement recu 700€',20,'2022-10-01 15:20:01',false),
(2,3,'virement recu 900€',20,'2022-10-01 15:20:01',true);
ALTER TABLE mouvement ALTER COLUMN idmouvement RESTART WITH 3;

