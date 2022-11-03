package projet.ejb.data;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the produit database table.
 * 
 */
@Entity
@Table( name = "produit" )
@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "idproduit")
	private Integer idproduit;
	
	@ManyToOne
	@JoinColumn(name="idcompte")
	private Compte compte;

	private Date datedebutenchere;

	private Date datefinenchere;

	private String description;

	private Boolean flagvente;

	private String nomproduit;

	private String photo;

	private double prix;

	public Produit() {
	}

	public Integer getIdproduit() {
		return this.idproduit;
	}

	public void setIdproduit(Integer idproduit) {
		this.idproduit = idproduit;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Date getDatedebutenchere() {
		return this.datedebutenchere;
	}

	public void setDatedebutenchere(Date datedebutenchere) {
		this.datedebutenchere = datedebutenchere;
	}

	public Date getDatefinenchere() {
		return this.datefinenchere;
	}

	public void setDatefinenchere(Date datefinenchere) {
		this.datefinenchere = datefinenchere;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFlagvente() {
		return this.flagvente;
	}

	public void setFlagvente(Boolean flagvente) {
		this.flagvente = flagvente;
	}

	public String getNomproduit() {
		return this.nomproduit;
	}

	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPrix() {
		return this.prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}