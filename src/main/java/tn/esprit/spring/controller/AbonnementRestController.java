package tn.esprit.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.services.AbonnementService;




@RestController

@CrossOrigin(origins="*")
public class AbonnementRestController {

	@Autowired
	AbonnementService  abonnementService ;
	

	@GetMapping("/get-Abonnes/{abonne_id}/{abonnement_id}")
	@ResponseBody
	public Long getAbonnes(@PathVariable("abonne_id") Long abonne_id,@PathVariable("abonnement_id") Long abonnement_id){
		
		
		return abonnementService.getAbonnes(abonne_id, abonnement_id);
	}
	
	@GetMapping("/Mes-Abonnes/{abonne_id}")
	@ResponseBody
	public List<Object>  MesAbonnes(@PathVariable("abonne_id") Long abonne_id){
		
		return abonnementService.MesAbonnes(abonne_id);
		
	}


	@GetMapping("/Abonnements/{Abonnement_id}")
	@ResponseBody
	public List<Object>  Abonnements(@PathVariable("Abonnement_id") Long Abonnement_id){
		
		return abonnementService.Abonnements(Abonnement_id);
		
	}
	
	
	@GetMapping("/nombre-Abonnes/{abonne_id}")
	@ResponseBody
	public int NombreFollow(@PathVariable("abonne_id") Long abonne_id){
		
		return abonnementService.nombreAbonnes(abonne_id);
		
	}
	
	
	@GetMapping("/Abonnement-Service/{abonne_id}/{Abonnement_id}")
	@ResponseBody
	public Map<String,Abonnement> AbonnementService(@PathVariable("abonne_id") Long abonne_id , @PathVariable("Abonnement_id") Long Abonnement_id){
		
		return abonnementService.AbonnementService(abonne_id, Abonnement_id);
		
	}

}
