package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoEnchere;
import projet.ejb.data.Enchere;

@Stateless
@Local
@TransactionAttribute(MANDATORY)
public class DaoEnchere implements IDaoEnchere {

	// Champs
	@PersistenceContext
	private EntityManager em;

	@Override
	public int inserer(Enchere enchere) {
		em.persist(enchere);
		em.flush();
		return enchere.getIdenchere();
	}

	@Override
	public void modifier(Enchere enchere) {
		em.merge(enchere);

	}

	@Override
	public void supprimer(int idEnchere) {
		Enchere e = retrouver(idEnchere);
		em.remove(e);

	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public Enchere retrouver(int idEnchere) {
		return em.find(Enchere.class, idEnchere);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Enchere> listerTout() {
		em.clear();
		var jpql = "SELECT E FROM Enchere E";
		var query = em.createQuery(jpql, Enchere.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public int compterEnchere(int idProduit) {
		em.clear();
		var jpql = "SELECT count(E) FROM Enchere E WHERE E.produit.idproduit =: idProduit";
		var query = em.createQuery(jpql, Long.class);
		query.setParameter("idProduit", idProduit);
		return query.getSingleResult().intValue();
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public Enchere dernierEnchere(int idProduit) {
		em.clear();
		var jpql = "SELECT E FROM Enchere E WHERE E.prixenchere = (SELECT MAX(E.prixenchere) FROM Enchere E WHERE E.produit.idproduit =: idProduit)";
		var query = em.createQuery(jpql, Enchere.class);
		query.setParameter("idProduit", idProduit);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
