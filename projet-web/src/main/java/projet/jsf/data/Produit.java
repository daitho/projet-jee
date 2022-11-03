package projet.jsf.data;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
public class Produit implements Serializable {

	private Integer idproduit;
	
	private Compte compte;

	@NotNull(message = "La date de début d'enchère doit être renseigné")
	private Date datedebutenchere;

	@NotNull(message = "La date de fin d'enchère doit être renseigné")
	private Date datefinenchere;

	@NotBlank(message = "La description doit être renseigné")
	@Size(max = 255, message = "Valeur trop longue pour le pseuo : 255 car. maxi")
	private String description;

	private Boolean flagvente;

	@NotBlank(message = "Le nom du produit doit être renseigné")
	@Size(max = 100, message = "Valeur trop longue pour le pseuo : 100 car. maxi")
	private String nomproduit;

	@NotBlank(message = "La photo doit être renseigné")
	@Size(max = 100, message = "Valeur trop longue pour le pseuo : 100 car. maxi")
	private String photo;

	@NotNull(message = "La prix doit être renseigné")
	@Min(value = 0, message = "Le prix ne doit pas être inférieur à zéro")
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