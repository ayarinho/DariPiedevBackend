package tn.esprit.spring.services;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.User;



public interface AbonnementService {
	public List<Object> MesAbonnes(Long abonne_id);
	public List<Object>Abonnements(Long Abonnement_id);
	public int nombreAbonnes(Long abonne_id);
	//public int nbreFollowing(Long id);
	public Map<String,Abonnement>  AbonnementService(long abonne_id, long Abonnement_id);
	public boolean findAbonnes(long follow_id , long followed_id);
	public Long getAbonnes(Long abonne_id,Long abonnement_id);

}
