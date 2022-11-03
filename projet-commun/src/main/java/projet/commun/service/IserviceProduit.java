package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoEnchere;
import projet.commun.dto.DtoProduit;
import projet.commun.exception.ExceptionValidation;

public interface IserviceProduit {
	
	int				inserer( DtoProduit dtoProduit ) throws ExceptionValidation;

	void			modifier( DtoProduit dtoProduit ) throws ExceptionValidation; 
	
	void			modifierCompte( DtoCompte dtoCompte ) throws ExceptionValidation;

	void			supprimer( int idProduit ) throws ExceptionValidation;

	DtoProduit 		retrouver( int idProduit ) ;
	
	DtoCompte 		retrouverCompte( int idCompte ) ;
	

	DtoEnchere dernierEnchere(int idProduit);

	List<DtoProduit>	listerTout() ;
	List<DtoProduit>	listerToutPoduitUsager(Integer idCompte) ;
	
	// lister les produits pas encore en enchere
	List<DtoProduit> listerProduitEncourDenchere();

		
	// lister les produits en enchere
	List<DtoProduit> listerProduitEnEnchere();


}
