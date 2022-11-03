package projet.ejb.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoEnchere;
import projet.commun.dto.DtoMouvement;
import projet.commun.dto.DtoProduit;
import projet.ejb.data.Compte;
import projet.ejb.data.Enchere;
import projet.ejb.data.Mouvement;
import projet.ejb.data.Produit;

 
@Mapper( componentModel = "cdi" )
public interface IMapperEjb {  
	
	static final IMapperEjb INSTANCE = Mappers.getMapper( IMapperEjb.class );
	
	
	// Compte
	
	Compte map( DtoCompte source );
	
	DtoCompte map( Compte source );
	
    Produit map( DtoProduit source );
	
	DtoProduit map( Produit source );
	
    Enchere map( DtoEnchere source );
	
	DtoEnchere map( Enchere source );
	

    Mouvement map( DtoMouvement source );
	
	DtoMouvement map( Mouvement source );
	
	
	
}
