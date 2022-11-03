package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoMouvement;
import projet.commun.exception.ExceptionValidation;

public interface IserviceMouvement {
	

	int				inserer( DtoMouvement dtoMouvement ) throws ExceptionValidation;

	void			modifier( DtoCompte dtoMouvement ) throws ExceptionValidation; 
	
	void			modifierMouvment( DtoMouvement dtoMouvement ) throws ExceptionValidation; 

	void			supprimer( int idMouvement ) throws ExceptionValidation;

	DtoMouvement 	retrouver( int idMouvement ) ;

	List<DtoMouvement>	listerTout() ;
	
	List<DtoMouvement>	listerToutUser(int idCompte) ;


}
