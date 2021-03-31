package tn.esprit.spring.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.UserRepository;



@Service
public class AbonnementServiceImpl implements AbonnementService {
	@Autowired
	AbonnementRepository abonnementRepository;
	@Autowired
	ConnectedUserRepository  userRepo;

	@Override
	public List<Object> MesAbonnes(Long abonne_id) {  // houmaa  eli 3amlelhom abonner 

		return abonnementRepository.MesAbonnes(abonne_id);
	}

	@Override
	public List<Object> Abonnements(Long Abonnement_id) { // ena eli mabonnehom

		return abonnementRepository.Abonnements(Abonnement_id);
	}

	@Override
	public int nombreAbonnes(Long abonne_id) {

		return abonnementRepository.nombreAbonnes(abonne_id);
	}


	

	@Override
	public Map<String,Abonnement> AbonnementService(long abonne_id, long Abonnement_id) {
		
		ConnectedUser user1 = new ConnectedUser();
		
		ConnectedUser user2 = new ConnectedUser();
		
		List<ConnectedUser> user = (List<ConnectedUser>) userRepo.findAll();
		
		Map<String,Abonnement> result= new HashMap<String, Abonnement>();
		
		Long id =null;
		
		id = abonnementRepository.getAbonnes(abonne_id, Abonnement_id); //id de l'abonnement 
		  
		 String a ="youssef "+id;
		 
		 System.out.println(a);
		 
		  System.out.println(id);
		  
		if (a.equals("youssef null")) {
			
			abonnementRepository.insertAbonnes(abonne_id, Abonnement_id);
			
			System.out.println("Youssef");

			result.put("Abonnement with success  ", null);
	     		
		return result;
			
		} else {
			
			System.out.println("not youssef");
			
			Abonnement f = new Abonnement();
			
			List<Abonnement> Abonnements = (List<Abonnement>)abonnementRepository.findAll();
			
			for (Abonnement abonne : Abonnements) {
				
				if (abonne.getId().equals(id)) {
					
					f = abonne;
					
					result.put("Il est abonner déja ", f);
					
					break;
				}
				
				return result ;

			}
			
			return result;
			
			
			
		}
	
		
	}

	@Override
	public boolean findAbonnes(long abonne_id, long Abonnement_id) {
		
		Long id =null;
		
		  id = abonnementRepository.getAbonnes(abonne_id, Abonnement_id);
		  
		 String a ="youssef "+id;
		 System.out.println(a);
		System.out.println(id);
		
		if (a.equals("youssefnull")) {
			return false;
			
		}else {
			return true;
		}
	}

}
