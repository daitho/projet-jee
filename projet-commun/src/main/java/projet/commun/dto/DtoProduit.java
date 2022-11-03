package projet.commun.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DtoProduit implements Serializable {

	private Integer idproduit;
	
	private DtoCompte compte;

	private Date datedebutenchere;

	private Date datefinenchere;

	private String description;

	private Boolean flagvente;

	private String nomproduit;

	private String photo;

	private double prix;

	// constructeurs

	public DtoProduit() {

	}

	

	public DtoProduit(Integer idproduit, DtoCompte idcompte, Date datedebutenchere, Date datefinenchere,
			String description, Boolean flagvente, String nomproduit, String photo, double prix) {
		super();
		this.idproduit = idproduit;
		this.compte = idcompte;
		this.datedebutenchere = datedebutenchere;
		this.datefinenchere = datefinenchere;
		this.description = description;
		this.flagvente = flagvente;
		this.nomproduit = nomproduit;
		this.photo = photo;
		this.prix = prix;
	}



	public Integer getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(Integer idproduit) {
		this.idproduit = idproduit;
	}
	

	public DtoCompte getCompte() {
		return compte;
	}

	public void setCompte(DtoCompte idcompte) {
		this.compte = idcompte;
	}



	public Date getDatedebutenchere() {
		return datedebutenchere;
	}

	public void setDatedebutenchere(Date datedebutenchere) {
		this.datedebutenchere = datedebutenchere;
	}

	public Date getDatefinenchere() {
		return datefinenchere;
	}

	public void setDatefinenchere(Date datefinenchere) {
		this.datefinenchere = datefinenchere;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFlagvente() {
		return flagvente;
	}

	public void setFlagvente(Boolean flagvente) {
		this.flagvente = flagvente;
	}

	public String getNomproduit() {
		return nomproduit;
	}

	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
