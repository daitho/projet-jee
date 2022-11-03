package projet.jsf.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
public class Mouvement implements Serializable {

	
	private Integer idmouvement;

	private Timestamp datemouvement;

	@NotBlank(message = "Le libéllé doit être renseigné")
	@Size(max = 100, message = "Valeur trop longue pour le pseuo : 100 car. maxi")
	private String libelle;

	private Compte compte;
	
	@NotNull(message = "Le montant doit être renseigné")
	@Min(value = 0, message = "Le prix ne doit pas être inférieur à zéro")
	private double montant;
	
	private boolean statut;

	public Mouvement() {
	}

	public Integer getIdmouvement() {
		return this.idmouvement;
	}

	public void setIdmouvement(Integer idmouvement) {
		this.idmouvement = idmouvement;
	}

	public Timestamp getDatemouvement() {
		return this.datemouvement;
	}

	public void setDatemouvement(Timestamp datemouvement) {
		this.datemouvement = datemouvement;
	}

	public String getLibelle() {
		return this.libelle;
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

	public Compte getCompte() {
		return this.compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

}