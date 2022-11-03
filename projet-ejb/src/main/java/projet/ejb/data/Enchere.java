package projet.ejb.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the enchere database table.
 * 
 */
@Entity
@Table( name = "enchere" )
@NamedQuery(name="Enchere.findAll", query="SELECT e FROM Enchere e")
public class Enchere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "idenchere")
	private Integer idenchere;

	private Timestamp dateenchere;

	private double prixenchere;

	//bi-directional many-to-one association to Compte
	@ManyToOne
	@JoinColumn(name="idcompte")
	private Compte compte;

	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="idproduit")
	private Produit produit;

	public Enchere() {
	}

	public Integer getIdenchere() {
		return this.idenchere;
	}

	public void setIdenchere(Integer idenchere) {
		this.idenchere = idenchere;
	}

	public Timestamp getDateenchere() {
		return this.dateenchere;
	}

	public void setDateenchere(Timestamp dateenchere) {
		this.dateenchere = dateenchere;
	}

	public double getPrixenchere() {
		return this.prixenchere;
	}

	public void setPrixenchere(double prixenchere) {
		this.prixenchere = prixenchere;
	}

	public Compte getCompte() {
		return this.compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}