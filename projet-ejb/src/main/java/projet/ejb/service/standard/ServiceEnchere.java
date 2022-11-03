package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoEnchere;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IserviceEnchere;
import projet.ejb.dao.IDaoEnchere;
import projet.ejb.data.Enchere;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceEnchere implements IserviceEnchere {
	// Champs
		@Inject
		private IMapperEjb mapper;
		@Inject
		private IDaoEnchere daoEnchere;

	@Override
	public int inserer(DtoEnchere dtoEnchere) throws ExceptionValidation {
		verifierValiditeEnchere(dtoEnchere);
		int id = daoEnchere.inserer(mapper.map(dtoEnchere));
		return id;
	}

	@Override
	public void modifier(DtoEnchere dtoEnchere) throws ExceptionValidation {
		verifierValiditeEnchere(dtoEnchere);
		daoEnchere.modifier(mapper.map(dtoEnchere));

	}

	@Override
	public void supprimer(int idEnchere) throws ExceptionValidation {
		daoEnchere.supprimer(idEnchere);

	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoEnchere retrouver(int idEnchere) {
		return mapper.map(daoEnchere.retrouver(idEnchere));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoEnchere> listerTout() {
		List<DtoEnchere> liste = new ArrayList<>();
		for (Enchere enchere : daoEnchere.listerTout()) {
			liste.add(mapper.map(enchere));
		}
		return liste;
	}
	
	@Override
	public int compterEnchere(int idProduit) {
		int id = daoEnchere.compterEnchere(idProduit);
		return id;
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoEnchere dernierEnchere(int idProduit) {
		return mapper.map(daoEnchere.dernierEnchere(idProduit));
	}
	
	
	private void verifierValiditeEnchere(DtoEnchere dtoEnchere) throws ExceptionValidation {
		StringBuilder message = new StringBuilder();
		String prix = ""+dtoEnchere.getDateenchere();

		if (dtoEnchere.getPrixenchere()==0  || prix.length()<=0) {
			message.append("\nLe prix doit être renseigner.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

}
