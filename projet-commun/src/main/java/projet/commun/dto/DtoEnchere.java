package projet.commun.dto;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class DtoEnchere implements Serializable {

	private Integer idenchere;

	private Timestamp dateenchere;

	private double prixenchere;


	private DtoCompte compte;


	private DtoProduit produit;
	
	//Constructeurs
	
   public DtoEnchere() {
		
	}

	public DtoEnchere(Integer idenchere, Timestamp dateenchere, double prixenchere, DtoCompte compte,
			DtoProduit produit) {
		super();
		this.idenchere = idenchere;
		this.dateenchere = dateenchere;
		this.prixenchere = prixenchere;
		this.compte = compte;
		this.produit = produit;
	}


	public Integer getIdenchere() {
		return idenchere;
	}


	public void setIdenchere(Integer idenchere) {
		this.idenchere = idenchere;
	}

	public Timestamp getDateenchere() {
		return dateenchere;
	}


	public void setDateenchere(Timestamp dateenchere) {
		this.dateenchere = dateenchere;
	}

	public double getPrixenchere() {
		return prixenchere;
	}


	public void setPrixenchere(double prixenchere) {
		this.prixenchere = prixenchere;
	}

	public DtoCompte getCompte() {
		return compte;
	}


	public void setCompte(DtoCompte compte) {
		this.compte = compte;
	}


	public DtoProduit getProduit() {
		return produit;
	}

	public void setProduit(DtoProduit produit) {
		this.produit = produit;
	}
	

}
