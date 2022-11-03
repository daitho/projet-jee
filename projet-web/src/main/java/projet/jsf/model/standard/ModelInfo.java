package projet.jsf.model.standard;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@RequestScoped
@Named
public class ModelInfo {
	
	// Champs
	
	private String 		titre;
	
	private String		texte;
	
	private double montant;

	
	// Getters & Setters
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	

}
