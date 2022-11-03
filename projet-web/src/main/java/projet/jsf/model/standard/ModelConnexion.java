package projet.jsf.model.standard;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoCompte;
import projet.commun.service.IServiceConnexion;
import projet.jsf.data.Compte;
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;


@RequestScoped
@Named
public class ModelConnexion {

	// Champs

	private Compte			courant;
	
	private  static Compte courantCompte;
	
	private static DtoCompte dtocompte;

	@Inject
	private  CompteActif		compteActif;
	@Inject
	private ModelInfo		modelInfo;
	@EJB
	private IServiceConnexion serviceConnexion;


	// Getters 
	
	public Compte getCourant() {
		if ( courant == null ) {
			courant = new Compte();
		}
		return courant;
	}

	
	// Actons
	
	public String connect() {
	    
	    DtoCompte dto = serviceConnexion.sessionUtilisateurOuvrir( courant.getPseudo(), courant.getMotDePasse() );
	    setCourantCompte(courant);
	    setDtocompte(dto);
	    if ( dto != null ){
	    	
//		    try {
//			    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//				( (HttpServletRequest) ec.getRequest() ).login( courant.getPseudo(), courant.getMotDePasse() );
//			} catch (ServletException e) {
//				throw new RuntimeException( e );
//			}

	        compteActif.setPseudo( dto.getPseudo() );
	        compteActif.setRoles( dto.getRoles() );
	        
	        modelInfo.setMontant(dto.getCredit());
	    	modelInfo.setTitre( dto.getNom()+" "+dto.getPrenom() );
	    	modelInfo.setTexte("Ici, la satisfaction est notre priorité.");
		    return "info";

	    } else {
	        UtilJsf.messageError( "Pseudo ou mot de passe invalide." );
	    	return null;
	    }
	}


	public static DtoCompte getDtocompte() {
		return dtocompte;
	}


	public static void setDtocompte(DtoCompte dtocompte) {
		ModelConnexion.dtocompte = dtocompte;
	}


	public Compte getCourantCompte() {
		return courantCompte;
	}


	public void setCourantCompte(Compte courantStat) {
		ModelConnexion.courantCompte = courantStat;
	}	
}
