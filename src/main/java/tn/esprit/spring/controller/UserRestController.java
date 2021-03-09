package tn.esprit.spring.controller;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import tn.esprit.spring.entities.Role;

import tn.esprit.spring.entities.User;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.CustomUserDetailsService;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.services.ReclamationService;
import tn.esprit.spring.util.JwtUtil;

@RestController

@CrossOrigin(origins="*")


public class UserRestController {
	
	@Autowired
	
	IUserService iuserService;
	

	@Autowired
	
	ReclamationService  reclamationservice ;
	
	private static final Logger logger = Logger.getLogger(UserRestController.class);
	
	
    
    
	@PostMapping("/add-user/{password}")
	@ResponseBody
	
	public User Add(@RequestBody User  client,@PathVariable("password") String password) throws Exception{
		
		return iuserService.ajouterClient(client, password);
	}
	
	@PostMapping("/add-admin")
	@ResponseBody
	
	public User AddAdmin(@RequestBody User  admin) throws Exception{
		
		return iuserService.ajouterAdmin(admin);
	}
	  
	
	@PostMapping("/authentification/{email}/{password}")
	@ResponseBody
	
	public String Authentification(@PathVariable("email") String email, @PathVariable("password") String password) throws Exception{
		
		return iuserService.authentification(email, password);
	}
	
	
	@PostMapping("/forget-Password/{email}")
	@ResponseBody
	public String forgetPassword(@PathVariable("email") String email){
		
		return iuserService.forgetPassword(email);
	}
	
	
	@CrossOrigin(origins="*")
	@PostMapping("/debloquer-Compte/{email}")
	@ResponseBody
	public String deblockCompte(@PathVariable("email") String email){
		
		return iuserService.deblockCompte(email);
		
	}
		

}
