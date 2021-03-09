package tn.esprit.spring.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.services.ReclamationService;


@RestController

@CrossOrigin(origins="*")


public class ReclamationRestController {
	
	@Autowired
	
	ReclamationService  reclamationservice ;
	
	private static final Logger logger = Logger.getLogger(ReclamationRestController.class);
	


	@PostMapping("/bad-words/{iduser}/{idcomment}")
	@ResponseBody
	public void AddReclamationAndBlokUser(@PathVariable("iduser") String iduser , @PathVariable("idcomment")String idComment){
		
		reclamationservice.AddReclamationAndBlokUserWhenCommentsContainsBadWords(iduser,idComment);
		
	}

}
