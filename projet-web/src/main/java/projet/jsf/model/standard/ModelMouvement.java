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

import projet.commun.dto.DtoMouvement;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceCompte;
import projet.commun.service.IserviceMouvement;
import projet.jsf.data.Compte;
import projet.jsf.data.Mouvement;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelMouvement implements Serializable {

	private List<Mouvement> listeNon;
	private List<Mouvement> listeOui;
	private List<Mouvement> listeUserNon;
	private List<Mouvement> listeUserOui;
	private Mouvement courant;

	@EJB
	private IserviceMouvement serviceMouvement;
	@EJB
	private IServiceCompte serviceCompte;

	@Inject
	private IMapper mapper;

	// Getters
	public List<Mouvement> getListeOui() {
		if (listeOui == null) {
			listeOui = new ArrayList<>();
			for (DtoMouvement dto : serviceMouvement.listerTout()) {
				if (dto.isStatut() == true) {
					listeOui.add(mapper.map(dto));
				}
			}
		}
		return listeOui;
	}

	public List<Mouvement> getListeNon() {
		if (listeNon == null) {
			listeNon = new ArrayList<>();
			for (DtoMouvement dto : serviceMouvement.listerTout()) {
				if (dto.isStatut() == false) {
					listeNon.add(mapper.map(dto));
				}
			}
		}
		return listeNon;
	}

	// Getters
	public List<Mouvement> getListeUserOui() {
		if (listeUserOui == null) {
			listeUserOui = new ArrayList<>();
			for (DtoMouvement dto : serviceMouvement.listerToutUser((int) ModelConnexion.getDtocompte().getId())) {
				if (dto.isStatut() == true) {
					listeUserOui.add(mapper.map(dto));
				}
			}
		}
		return listeUserOui;
	}

	public List<Mouvement> getListeUserNon() {
		if (listeUserNon == null) {
			listeUserNon = new ArrayList<>();
			for (DtoMouvement dto : serviceMouvement.listerToutUser((int) ModelConnexion.getDtocompte().getId())) {
				if (dto.isStatut() == false) {
					listeUserNon.add(mapper.map(dto));
				}
			}
		}
		return listeUserNon;
	}

	public Mouvement getCourant() {
		if (courant == null) {
			courant = new Mouvement();
		}
		return courant;
	}

	// Initialisaitons
	public String actualiserCourant() {
		if (courant != null) {
			DtoMouvement dto = serviceMouvement.retrouver(courant.getIdmouvement());
			if (dto == null) {
				UtilJsf.messageError("Le compte demandé n'existe pas");
				return "test/liste";
			} else {
				courant = mapper.map(dto);
			}
		}
		return null;
	}

	// Actions
	public String validerMiseAJour() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();

		Timestamp timestamp = Timestamp.valueOf("" + format.format(calendar.getTime()));
		courant.setDatemouvement(timestamp);
		courant.setCompte(mapper.map(ModelConnexion.getDtocompte()));
		courant.setLibelle("Virement de "+courant.getMontant());
		try {
			if (courant.getIdmouvement() == null) {
				serviceMouvement.inserer(mapper.map(courant));
			}
			UtilJsf.messageInfo("Mise à jour effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	// Actions
	public String validerMiseAJourDepot() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();

		Timestamp timestamp = Timestamp.valueOf("" + format.format(calendar.getTime()));
		courant.setDatemouvement(timestamp);
		courant.setCompte(mapper.map(ModelConnexion.getDtocompte()));
		courant.setLibelle("Dépôt de "+courant.getMontant());
		courant.setStatut(true);

		double montant = (Double) courant.getMontant() + (Double)ModelConnexion.getDtocompte().getCredit();
		Compte dtocompte = mapper.map(ModelConnexion.getDtocompte());
		dtocompte.setCredit(montant);
		try {
			serviceMouvement.inserer(mapper.map(courant));
			serviceMouvement.modifier(mapper.map(dtocompte));
			UtilJsf.messageInfo("Mise à jour effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	public String supprimer(Mouvement item) {
		try {
			serviceMouvement.supprimer(item.getIdmouvement());
			UtilJsf.messageInfo("Suppression effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
		}
		return null;
	}
	
	public String validerMouvement(Mouvement item) {
		try {
			item.setStatut(true);
			if(item.isStatut()==true) {
				double montant = (Double)item.getCompte().getCredit() - (Double) item.getMontant();
				Compte dtocompte = item.getCompte();
				dtocompte.setCredit(montant);
				
				serviceMouvement.modifierMouvment(mapper.map(item));
				serviceMouvement.modifier(mapper.map(dtocompte));
				UtilJsf.messageInfo("Validation virement effectué avec succès.");
			}else {
				UtilJsf.messageError("Erreur de Validation");
				
			}
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
		}
		return null;
	}

	public void validatePrix(FacesContext context, UIComponent comp, Object value) {

		System.out.println("Vérification du montant...");

		String str = "" + value;
		double mno = Double.parseDouble(str);
		if (mno > ModelConnexion.getDtocompte().getCredit()) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage("Votre montant enter ne doit pas dépassé votre crédit");
			context.addMessage(comp.getClientId(context), message);

		}

	}

}
