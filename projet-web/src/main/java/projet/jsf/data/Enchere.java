package projet.jsf.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@SuppressWarnings("serial")
public class Enchere implements Serializable {

	private Integer idenchere;

	private Timestamp dateenchere;

	@NotNull(message = "Le prix doit être renseigné")
	@Min(value = 0, message = "Le prix ne doit pas être inférieur à zéro")
	private double prixenchere;

	private Compte compte;

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