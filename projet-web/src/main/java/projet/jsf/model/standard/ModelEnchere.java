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

import projet.commun.dto.DtoEnchere;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IserviceEnchere;
import projet.jsf.data.Enchere;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelEnchere implements Serializable {
	// Champs

	private List<Enchere> liste;
	private Enchere courant;

	@EJB
	private IserviceEnchere serviceEnchere;

	@Inject
	private IMapper mapper;

	// Getters

	public List<Enchere> getListe() {
		if (liste == null) {
			liste = new ArrayList<>();
			for (DtoEnchere dto : serviceEnchere.listerTout()) {
				if (dto.getCompte().getId()==ModelConnexion.getDtocompte().getId()) {
					liste.add(mapper.map(dto));
				}
			}
		}
		return liste;
	}

	public Enchere getCourant() {
		if (courant == null) {
			courant = new Enchere();
		}
		return courant;
	}
	
	public Enchere getDernierEnchere() {
		Enchere enchere = null;
		if (courant == null) {
			DtoEnchere dtoenchere =  serviceEnchere.dernierEnchere(ModelProduit.getProduitStatic().getIdproduit());
			enchere = mapper.map(dtoenchere);
			courant = mapper.map(dtoenchere);
		}
		return enchere;
	}
	
	public double getNombreEnchere() {
		double nombre = 0;
		if (courant == null) {
			nombre =  serviceEnchere.compterEnchere(courant.getProduit().getIdproduit());
		}
		return nombre;
	}

	// Initialisaitons
	public String actualiserCourant() {
		if (courant != null) {
			DtoEnchere dto = serviceEnchere.retrouver(courant.getIdenchere());
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
		

        Timestamp timestamp = Timestamp.valueOf(""+format.format(calendar.getTime()));
		courant.setDateenchere(timestamp);
		try {
			serviceEnchere.inserer(mapper.map(courant));
			UtilJsf.messageInfo("Mise à jour effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	public void setCourant(Enchere courant) {
		this.courant = courant;
	}

	public String supprimer(Enchere item) {
		try {
			serviceEnchere.supprimer(item.getIdenchere());
			liste.remove(item);
			UtilJsf.messageInfo("Suppression effectuée avec succès.");
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
		}
		return null;
	}
	
	//Save
	public String validerMiseAJourEnchere() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();

		Timestamp timestamp = Timestamp.valueOf("" + format.format(calendar.getTime()));
		DtoEnchere enchere = new DtoEnchere(null, timestamp, courant.getPrixenchere(), mapper.map(courant.getCompte()), mapper.map(ModelProduit.getProduitStatic()));
		try {
			serviceEnchere.inserer(enchere);
			UtilJsf.messageInfo("L'enchère à été effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public void validatePrix(FacesContext context, UIComponent comp, Object value) {

		System.out.println("Validation en cours...");

		double mno = Double.parseDouble((String) value);
		
		if (mno<ModelConnexion.getDtocompte().getCredit()) {
			if (courant!=null) {
				if (mno < courant.getPrixenchere() || mno < ModelProduit.getPrixcourant()) {
					((UIInput) comp).setValid(false);

					FacesMessage message = new FacesMessage("Votre prix doit dépassé le prix du produit et de la dernière enchère");
					context.addMessage(comp.getClientId(context), message);

				}
			}else {
				if (mno < ModelProduit.getPrixcourant()) {
					((UIInput) comp).setValid(false);

					FacesMessage message = new FacesMessage("Votre prix doit dépassé le prix du produit");
					context.addMessage(comp.getClientId(context), message);

				}
			}
		}else {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage("Votre montant enter ne doit pas dépassé votre crédit");
			context.addMessage(comp.getClientId(context), message);
			
		}
		

	}
}
