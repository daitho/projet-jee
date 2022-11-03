package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoEnchere;
import projet.commun.exception.ExceptionValidation;

public interface IserviceEnchere {
	
	
	int				inserer( DtoEnchere dtoEnchere ) throws ExceptionValidation;

	void			modifier( DtoEnchere dtoEnchere ) throws ExceptionValidation; 

	void			supprimer( int idEnchere ) throws ExceptionValidation;

	DtoEnchere 		retrouver( int idEnchere ) ;

	List<DtoEnchere>	listerTout() ;
	
	//le nombre d'enchere deja faits
    int		    compterEnchere(int idProduit );
	
	//le montant de la derniere enchere
    DtoEnchere     dernierEnchere(int idProduit);


}
