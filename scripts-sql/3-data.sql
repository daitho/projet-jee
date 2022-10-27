SET search_path TO projet;


-- Supprime toutes les données




DELETE FROM role;
DELETE FROM compte;
DELETE FROM produit;
DELETE FROM enchere;
DELETE FROM mouvement;

-- Insère les données

-- Compte

INSERT INTO compte (idcompte,idmouvement, pseudo, motdepasse, email ) VALUES 
( 1, 'geek', 'geek', 'geek@jfox.fr' ),
( 2, 'chef', 'chef', 'chef@jfox.fr' ),
( 3, 'job', 'job', 'job@jfox.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;


-- Role

INSERT INTO role (idcompte, role) VALUES 
( 1, 'ADMINISTRATEUR' ),
( 1, 'UTILISATEUR' ),
( 2, 'UTILISATEUR' ),
( 3, 'UTILISATEUR' );

-- Produit

INSERT INTO produit (idproduit ,nomproduit,photo,description,prix,datadebutenchere,datefinenchere,flagvente) VALUES 
( 1, 'Iphone ','X',700,2022-10-28,2022-11-15,false ),
( 2, 'ACER','10EME Generation',1000,2022-10-28,2022-11-15,false );
 
INSERT INTO enchere (idenchere,idproduit,idcompte,prixenchere,dateenchere) VALUES
(1,1,1,900,2022-10-01 15:20:01),
(1,2,3,1200,2022-10-01 15:20:01);

INSERT INTO mouvement (idmouvement,idcompte,libelle,datemouvement) VALUES 
(1,1,'virement recu 700€',2022-10-01 15:20:01);
