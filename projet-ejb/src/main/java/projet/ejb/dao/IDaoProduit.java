package projet.ejb.dao;

import java.text.ParseException;
import java.util.List;

import projet.ejb.data.Compte;
import projet.ejb.data.Enchere;
import projet.ejb.data.Produit;

public interface IDaoProduit{

	int inserer(Produit produit);

	void modifier(Produit produit);
	
	void modifierCompte(Compte compte);

	void supprimer(int idProduit);

	Produit retrouver(int idProduit);
	
	Compte retrouverCompte(int idCompte);

	List<Produit> listerTout();
	List<Produit> listerToutPoduitUsager(Integer idCompte);

	// lister les produits pas encore en enchere
	List<Produit> listerProduitEncourDenchere() throws ParseException;

	
	// lister les produits en enchere
	List<Produit> listerProduitEnEnchere() throws ParseException;

	Enchere dernierEnchere(int idProduit);

	

}
