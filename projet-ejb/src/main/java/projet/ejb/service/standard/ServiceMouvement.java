package projet.ejb.service.standard;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoMouvement;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IserviceMouvement;
import projet.ejb.dao.IDaoMouvement;
import projet.ejb.data.Mouvement;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceMouvement implements IserviceMouvement {
	
	// Champs
		@Inject
		private IMapperEjb mapper;
		@Inject
		private IDaoMouvement daoMouvement;

	@Override
	public int inserer(DtoMouvement dtoMouvement) throws ExceptionValidation {
		verifierValiditeMouvement(dtoMouvement);
		int id = daoMouvement.inserer(mapper.map(dtoMouvement));
		return id;
	}

	@Override
	public void modifier(DtoCompte dtoMouvement) throws ExceptionValidation {
		daoMouvement.modifier(mapper.map(dtoMouvement));

	}

	@Override
	public void supprimer(int idMouvement) throws ExceptionValidation {
		daoMouvement.supprimer(idMouvement);

	}

	@Override
	public DtoMouvement retrouver(int idMouvement) {
		return mapper.map(daoMouvement.retrouver(idMouvement));
	}

	@Override
	public List<DtoMouvement> listerTout() {
		List<DtoMouvement> liste = new ArrayList<>();
		for (Mouvement enchere : daoMouvement.listerTout()) {
			liste.add(mapper.map(enchere));
		}
		return liste;
	}
	@Override
	public List<DtoMouvement> listerToutUser(int idCompte) {
		List<DtoMouvement> liste = new ArrayList<>();
		for (Mouvement enchere : daoMouvement.listerToutUser(idCompte)) {
			liste.add(mapper.map(enchere));
		}
		return liste;
	}
	
	private void verifierValiditeMouvement(DtoMouvement dtoMouvement) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();
		String prix = ""+dtoMouvement.getMontant();

		if (dtoMouvement.getLibelle()==null  || dtoMouvement.getLibelle().isEmpty()) {
			message.append("\nVeillez renseigner le libéllé du mouvement.");
		}
		
		if (dtoMouvement.getMontant() <=0 || prix.isEmpty()) {
			message.append("\nLe montant est incorrect où doit être renseigner.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

	@Override
	public void modifierMouvment(DtoMouvement dtoMouvement) throws ExceptionValidation {
		daoMouvement.modifierMouvement(mapper.map(dtoMouvement));
		
	}

}
