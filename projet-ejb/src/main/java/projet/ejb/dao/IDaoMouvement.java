package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Compte;
import projet.ejb.data.Mouvement;

public interface IDaoMouvement {
	
	int			inserer(Mouvement mouvement );

	void 		supprimer( int idMouvement );

    Mouvement 		retrouver( int idMouvement );

	List<Mouvement> listerTout();
	
	List<Mouvement> listerToutUser(int idCompte);

	void modifier(Compte mouvement);
	
	void modifierMouvement(Mouvement mouvement);

}
