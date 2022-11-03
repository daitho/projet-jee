package projet.commun.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DtoCompte implements Serializable  {
	
	// Champs
	private Integer		id;
	
	private String     nom;
	
	private String     prenom;
	
	private String		pseudo;
	
	private String		motDePasse;
	
	private String		email;
	
	private double		credit;
	
	private String roles;
	
	
	// Constructeurs
	
	public DtoCompte() {
	}

	public DtoCompte(int id, String nom, String prenom, String pseudo, String motDePasse, String email, double credit,
			String roles) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.credit = credit;
		this.roles = roles;
	}




	// Getters & setters

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
	public boolean isInRole( String role ) {
		
		if ( role != null ) {
			if ( roles.equals(role) ) {
				return true;
			}
		}
		return false;
	}
}
