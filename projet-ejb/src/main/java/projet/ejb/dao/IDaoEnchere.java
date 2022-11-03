package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Enchere;

public interface IDaoEnchere {
	int			inserer( Enchere enchere );

	void 		modifier( Enchere enchere );

	void 		supprimer( int idEnchere );

   Enchere 		retrouver( int idEnchere );

	List<Enchere> listerTout();
	
	//le nombre d'enchere deja faits
    int		    compterEnchere(int idProduit );
	
	//le montant de la derniere enchere
    Enchere     dernierEnchere(int idProduit);
	
	
}
