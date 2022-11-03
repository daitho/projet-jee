package projet.jsf.data;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class Compte implements Serializable {

	// Champs

	private Integer id;

	@NotBlank(message = "Le pseudo doit être renseigné")
	@Size(max = 25, message = "Valeur trop longue pour le pseuo : 25 car. maxi")
	private String pseudo;

	@NotBlank(message = "Le mot de passe doit être renseigné")
	@Size(max = 25, message = "Valeur trop longue pour le mot de passe : 25 car. maxi")
	private String motDePasse;

	@NotBlank(message = "L'adresse e-mail doit être renseigné")
	@Size(max = 25, message = "Valeur trop longue pour l'adresse e-mail : 100 car. maxi")
	@Email(message = "Adresse e-mail invalide")
	private String email;

	@NotNull(message = "Le credit doit être renseigné")
	private double credit;

	@NotBlank(message = "Le nom doit être renseigné")
	@Size(max = 25, message = "Valeur trop longue pour le nom : 25 car. maxi")
	private String nom;

	@NotBlank(message = "Le pseudo doit être renseigné")
	@Size(max = 25, message = "Valeur trop longue pour le prenom : 55 car. maxi")
	private String prenom;

	private String roles;

	// Constructeurs

	public Compte() {
	}

	public Compte(Integer id, String pseudo, String motDePasse, String email, double credit, String nom, String prenom,
			String roles) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.credit = credit;
		this.nom = nom;
		this.prenom = prenom;
		this.roles = roles;
	}

	// Getters & setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isInRole(String role) {
		return roles.contains(role);
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
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

	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		var other = (Compte) obj;
		return Objects.equals(id, other.id);
	}

}
