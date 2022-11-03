package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoProduit;
import projet.ejb.data.Compte;
import projet.ejb.data.Enchere;
import projet.ejb.data.Produit;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoProduit implements IDaoProduit {
	
	// Champs
	@PersistenceContext
	private EntityManager	em;

	@Override
	public int inserer(Produit produit) {
		em.persist(produit);
		em.flush();
		return produit.getIdproduit();
	}

	@Override
	public void modifier(Produit produit) {
	  em.merge(produit);	
	}
	
	@Override
	public void modifierCompte(Compte compte) {
	  em.merge(compte);	
	}


	@Override
	public void supprimer(int idProduit) {
		
	  Produit p=retrouver(idProduit);
	  em.remove(p);

	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Produit retrouver(int idProduit) {
		return em.find(Produit.class,idProduit);
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Compte retrouverCompte(int idCompte) {
		return em.find(Compte.class,idCompte);
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Produit> listerTout() {
		em.clear();
		var jpql = "SELECT p FROM Produit p ORDER BY p.nomproduit";
		var query = em.createQuery( jpql, Produit.class );
		return query.getResultList();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Produit> listerToutPoduitUsager(Integer idCompte) {
		em.clear();
		var jpql = "SELECT p FROM Produit p WHERE p.compte.id=:idCompte ORDER BY p.nomproduit";
		var query = em.createQuery( jpql, Produit.class );
		query.setParameter( "idCompte", idCompte);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Produit> listerProduitEncourDenchere() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		Date dateDebut = sdf.parse(""+format.format(calendar.getTime()));
        
		em.clear();
		var jpql = "SELECT p FROM Produit p WHERE p.datedebutenchere>:dateActuelle";
		var query = em.createQuery( jpql, Produit.class );
		query.setParameter( "dateActuelle", dateDebut);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Produit> listerProduitEnEnchere() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		Date dateFin = sdf.parse(""+format.format(calendar.getTime()));
		boolean flag=false;
        
		em.clear();
		var jpql = "SELECT p FROM Produit p WHERE p.datefinenchere>=:dateActuelle and p.flagvente=:flag ";
		var query = em.createQuery( jpql, Produit.class );
		query.setParameter( "dateActuelle", dateFin);
		query.setParameter( "flag", flag);
		return query.getResultList();
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
