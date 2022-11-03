package projet.jsf.model.standard;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoEnchere;
import projet.commun.dto.DtoProduit;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IserviceEnchere;
import projet.commun.service.IserviceProduit;
import projet.jsf.data.Compte;
import projet.jsf.data.Enchere;
import projet.jsf.data.Produit;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelProduit implements Serializable {
	// Champs

	private List<Produit> liste;
	private List<Produit> liste1;
	private List<Produit> liste2;

	private Produit courant;
	private DtoEnchere courantProduit;
	private Enchere courantEnchere = new Enchere();
	
	private static double prixcourant;

	private static Produit produitStatic;

	@EJB
	private IserviceProduit serviceProduit;
	@EJB
	private IserviceEnchere serviceEnchere;

	@Inject
	private IMapper mapper;

	// Getters

	public List<Produit> getListe() {
		if (liste == null) {
			liste = new ArrayList<>();
			for (DtoProduit dto : serviceProduit.listerTout()) {
				liste.add(mapper.map(dto));
			}
		}
		return liste;
	}

	public DtoCompte getCompte() {
		return ModelConnexion.getDtocompte();
	}

	public List<Produit> getListeUsager() {
		if (liste == null) {
			liste = new ArrayList<>();
			for (DtoProduit dto : serviceProduit.listerToutPoduitUsager(ModelConnexion.getDtocompte().getId())) {
				liste.add(mapper.map(dto));
			}
		}
		return liste;
	}

	public List<Produit> listerProduitEncourDenchere() throws ParseException {
		if (liste1 == null) {
			liste1 = new ArrayList<>();
			for (DtoProduit dto : serviceProduit.listerProduitEncourDenchere()) {
				liste1.add(mapper.map(dto));
			}
		}
		return liste1;
	}

	public List<Produit> listerProduitEnEnchere() throws ParseException {
		if (liste2 == null) {
			liste2 = new ArrayList<>();
			for (DtoProduit dto : serviceProduit.listerProduitEnEnchere()) {
				liste2.add(mapper.map(dto));
			}
		}
		return liste2;
	}

	public Produit getCourant() {
		if (courant == null) {
			courant = new Produit();
		}
		return courant;
	}

	// Initialisaitons
	public String actualiserCourant() {
		if (courant != null) {
			DtoProduit dto = serviceProduit.retrouver(courant.getIdproduit());
			DtoEnchere dtoenchere = serviceEnchere.dernierEnchere(courant.getIdproduit());
			if (dto == null) {
				UtilJsf.messageError("Le compte demandé n'existe pas");
				return "usager/liste";
			} else {
				courant = mapper.map(dto);
				setProduitStatic(mapper.map(dto));
				courantProduit = dtoenchere;
				setPrixcourant(dtoenchere.getPrixenchere());
			}
		}
		return null;
	}

	// Actions
	public String validerMiseAJour() throws ParseException {
		courant.setCompte(mapper.map(getCompte()));
		courant.setPhoto("images:star.png");
		try {
			if (courant.getIdproduit() == null) {
				serviceProduit.inserer(mapper.map(courant));
			} else {
				serviceProduit.modifier(mapper.map(courant));
			}
			UtilJsf.messageInfo("Mise à jour effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	public String supprimer(Produit item) {
		try {
			serviceProduit.supprimer(item.getIdproduit());
			liste.remove(item);
			UtilJsf.messageInfo("Suppression effectuée avec succès.");
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
		}
		return null;
	}
	
	public String validerFlag(Produit item) {
		DtoEnchere dtoenchere = serviceProduit.dernierEnchere(item.getIdproduit());
		Compte compteVendeur = item.getCompte();
		Compte compteAcheteur = mapper.map(dtoenchere.getCompte());
		
		double vendeur = (Double)compteVendeur.getCredit() + (Double)dtoenchere.getPrixenchere();
		compteVendeur.setCredit(vendeur);
		double acheteur =(Double)compteAcheteur.getCredit() - (Double)dtoenchere.getPrixenchere();
		compteAcheteur.setCredit(acheteur);
		item.setFlagvente(true);
		
		try {
			if (item.getFlagvente()==true) {
				serviceProduit.modifier(mapper.map(item));
				serviceProduit.modifierCompte(mapper.map(compteAcheteur));
				serviceProduit.modifierCompte(mapper.map(compteVendeur));
			}
			UtilJsf.messageInfo("Le Flag a été effectué avec succès.");
			return "test/liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
		}
		return null;
	}

	// Partir enchere

	public Enchere getDernierEnchere() {
		Enchere enchere = null;
		if (courant == null) {
			DtoEnchere dtoenchere = serviceEnchere.dernierEnchere(courant.getIdproduit());
			enchere = mapper.map(dtoenchere);
		}
		return enchere;
	}

	public static Produit getProduitStatic() {
		return produitStatic;
	}

	public static void setProduitStatic(Produit produitStatic) {
		ModelProduit.produitStatic = produitStatic;
	}

	// Actions
	public String validerMiseAJourEnchere() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();

		Timestamp timestamp = Timestamp.valueOf("" + format.format(calendar.getTime()));
		DtoEnchere enchere = new DtoEnchere(null, timestamp, courantEnchere.getPrixenchere(), getCompte(),
				mapper.map(courant));
		
		try {
			serviceEnchere.inserer(enchere);
			UtilJsf.messageInfo("L'enchère à été effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

//	public String actualiserCourantEnchere() {
//		if (courantEnchere != null) {
//			DtoEnchere dto = new DtoEnchere(getDernierEnchere().getIdenchere(), null, 0, getCompte(), null);
//			if (dto == null) {
//				UtilJsf.messageError("L'enchère demandé n'existe pas");
//				return "usager/enchere/liste";
//			} else {
//				courant = mapper.map(dto);
//			}
//		}
//		return null;
//	}

	public void setCourantProduit(DtoEnchere courantProduit) {
		this.courantProduit = courantProduit;
	}

	public void validatePrix(FacesContext context, UIComponent comp, Object value) {

		System.out.println("Validation en cours...");

		double mno = (Double)value;
		if (mno < ModelConnexion.getDtocompte().getCredit()) {
			if (courantEnchere != null) {
				if (mno < ((Double)getPrixcourant()+1) ) {
					((UIInput) comp).setValid(false);

					FacesMessage message = new FacesMessage(
							"Votre prix doit dépassé le prix de la dernière enchère");
					context.addMessage(comp.getClientId(context), message);
				}
			} else {
				if (mno < courant.getPrix()) {
					((UIInput) comp).setValid(false);

					FacesMessage message = new FacesMessage("Votre prix doit dépassé le prix du produit");
					context.addMessage(comp.getClientId(context), message);

				}
			}
		} else {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage("Votre montant enter ne doit pas dépassé votre crédit");
			context.addMessage(comp.getClientId(context), message);

		}

	}

	public DtoEnchere getCourantProduit() {
		if (courantProduit == null) {
			courantProduit = new DtoEnchere();
		}
		return courantProduit;
	}

	public static double getPrixcourant() {
		System.out.println(prixcourant);
		return prixcourant;
	}

	public static void setPrixcourant(double prixcourant) {
		ModelProduit.prixcourant = prixcourant;
	}

	public Enchere getCourantEnchere() {
		if (courantEnchere == null) {
			courantEnchere = new Enchere();
		}
		return courantEnchere;
	}


}
