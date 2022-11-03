package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoEnchere;
import projet.commun.dto.DtoProduit;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IserviceProduit;
import projet.ejb.dao.IDaoProduit;
import projet.ejb.data.Produit;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceProduit implements IserviceProduit {
	
	// Champs
		@Inject
		private IMapperEjb mapper;
		@Inject
		private IDaoProduit daoProduit;

	@Override
	public int inserer(DtoProduit dtoProduit) throws ExceptionValidation{
		try {
			verifierValiditeProduit(dtoProduit);
		} catch (ExceptionValidation | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = daoProduit.inserer(mapper.map(dtoProduit));
		return id;
	}

	@Override
	public void modifier(DtoProduit dtoProduit) throws ExceptionValidation {
		try {
			verifierValiditeProduit(dtoProduit);
		} catch (ExceptionValidation e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		daoProduit.modifier(mapper.map(dtoProduit));

	}
	
	@Override
	public DtoEnchere dernierEnchere(int idCompte) {
		return mapper.map(daoProduit.dernierEnchere(idCompte));

	}
	
	@Override
	public void modifierCompte(DtoCompte dtoCompte) throws ExceptionValidation {
		daoProduit.modifierCompte(mapper.map(dtoCompte));

	}

	@Override
	public void supprimer(int idProduit) throws ExceptionValidation {
		daoProduit.supprimer(idProduit);

	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoProduit retrouver(int idProduit) {
		return mapper.map(daoProduit.retrouver(idProduit));
	}
	
	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoCompte retrouverCompte(int idProduit) {
		return mapper.map(daoProduit.retrouverCompte(idProduit));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoProduit> listerTout() {
		List<DtoProduit> liste = new ArrayList<>();
		for (Produit produit : daoProduit.listerTout()) {
			liste.add(mapper.map(produit));
		}
		return liste;
	}
	
	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoProduit> listerToutPoduitUsager(Integer idCompte) {
		List<DtoProduit> liste = new ArrayList<>();
		for (Produit produit : daoProduit.listerToutPoduitUsager(idCompte)) {
			liste.add(mapper.map(produit));
		}
		return liste;
	}
	
	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoProduit> listerProduitEncourDenchere(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		Date dateDebut;
		List<DtoProduit> liste = new ArrayList<>();
		try {
			dateDebut = sdf.parse(""+format.format(calendar.getTime()));
			for (Produit produit : daoProduit.listerTout()) {
				if (produit.getDatedebutenchere().after(dateDebut)) {
					liste.add(mapper.map(produit));
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoProduit> listerProduitEnEnchere(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		Date dateDebut;
		List<DtoProduit> liste = new ArrayList<>();
		try {
			dateDebut = sdf.parse(""+format.format(calendar.getTime()));
			for (Produit produit : daoProduit.listerTout()) {
				if (produit.getDatedebutenchere().before(dateDebut) && produit.getFlagvente()==false) {
					liste.add(mapper.map(produit));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	private void verifierValiditeProduit(DtoProduit dtoProduit) throws ExceptionValidation, ParseException {

		StringBuilder message = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		Date dateDebut = sdf.parse(""+format.format(calendar.getTime()));

		if (dtoProduit.getNomproduit() == null || dtoProduit.getNomproduit().isEmpty()) {
			message.append("\nLe nom du produit est absent.");
		} 
		if (dtoProduit.getDescription() == null || dtoProduit.getDescription().isEmpty()) {
			message.append("\nLa description ne doit pas être vide.");
		} 
		if (dtoProduit.getDatedebutenchere() == null || dtoProduit.getDatedebutenchere().before(dateDebut)) {
			message.append("\nLa date de début ne doit pas être inférieur à la date actuelle.");
		}
		if (dtoProduit.getDatefinenchere() == null || dtoProduit.getDatedebutenchere().after(dtoProduit.getDatefinenchere())) {
			message.append("\nLa date de fin doit pas être supérieur à la date de début d'enchère.");
		}
		if (dtoProduit.getPhoto() == null || dtoProduit.getPhoto().isEmpty()) {
			message.append("\nLa photo doit être renseigner.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

}
