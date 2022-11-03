package projet.ejb.data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the mouvement database table.
 * 
 */
@Entity
@Table( name = "mouvement" )
@NamedQuery(name="Mouvement.findAll", query="SELECT m FROM Mouvement m")
public class Mouvement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "idmouvement")
	private Integer idmouvement;

	private Timestamp datemouvement;
	
	private double montant;

	private String libelle;

	//bi-directional many-to-one association to Compte
	@ManyToOne
	@JoinColumn(name="idcompte")
	private Compte compte;
	
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