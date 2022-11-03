package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoMouvement;
import projet.ejb.data.Compte;
import projet.ejb.data.Mouvement;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoMouvement implements IDaoMouvement {

	// Champs
	@PersistenceContext
	private EntityManager em;

	@Override
	public int inserer(Mouvement mouvement) {
		em.persist(mouvement);
		em.flush();
		return mouvement.getIdmouvement();
	}

	@Override
	public void modifier(Compte mouvement) {
		em.merge(mouvement);

	}
	
	@Override
	public void modifierMouvement(Mouvement mouvement) {
		em.merge(mouvement);

	}

	@Override
	public void supprimer(int idMouvement) {
		Mouvement m = retrouver(idMouvement);
		em.remove(m);

	}

	@Override
	public Mouvement retrouver(int idMouvement) {
		return em.find(Mouvement.class, idMouvement);
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Mouvement> listerTout() {
		em.clear();
		var jpql = "SELECT m FROM Mouvement m";
		var query = em.createQuery(jpql, Mouvement.class);
		return query.getResultList();
	}
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Mouvement> listerToutUser(int idCompte) {
		em.clear();
		var jpql = "SELECT m FROM Mouvement m WHERE m.compte.id =: idCompte ORDER BY idmouvement DESC";
		var query = em.createQuery(jpql, Mouvement.class);
		query.setParameter( "idCompte", idCompte);
		return query.getResultList();
	}

}
