package projet.commun.dto;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class DtoMouvement implements Serializable {
	
	private Integer idmouvement;

	private Timestamp datemouvement;

	private String libelle;
	
	private double montant;

	private DtoCompte compte;
	
	private boolean statut; 
	
	public DtoMouvement() {
		
	}

	public DtoMouvement(Integer idmouvement, Timestamp datemouvement, String libelle, double montant, DtoCompte compte,
			boolean statut) {
		this.idmouvement = idmouvement;
		this.datemouvement = datemouvement;
		this.libelle = libelle;
		this.montant = montant;
		this.compte = compte;
		this.statut = statut;
	}


	public Integer getIdmouvement() {
		return idmouvement;
	}

	public void setIdmouvement(Integer idmouvement) {
		this.idmouvement = idmouvement;
	}

	public Timestamp getDatemouvement() {
		return datemouvement;
	}

	public void setDatemouvement(Timestamp datemouvement) {
		this.datemouvement = datemouvement;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public DtoCompte getCompte() {
		return compte;
	}

	public void setCompte(DtoCompte compte) {
		this.compte = compte;
	}

	public boolean isStatut() {
		return statut;
	}
	public void setStatut(boolean statut) {
		this.statut = statut;
	}
	
	

}
