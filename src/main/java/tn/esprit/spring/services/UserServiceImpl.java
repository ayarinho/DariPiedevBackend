package tn.esprit.spring.services;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.util.JwtUtil;


@Service
public class UserServiceImpl implements IUserService {
	
 
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CryptWithSHA256  cryptaWithSHA256;
	
	@Autowired
	NotificationServeur notificationServeur;
	
	@Autowired
	Filter filter;

    @Autowired
    JwtUtil javautil ;
   
   @Autowired
    AuthenticationManager authenticationManager;
	
	
	public Map<String, User> ajouterClient(User client,String password) throws Exception {
		
		Role role=null;
		
		client.setRole(role.Client);
		
		Map<String,User> result= new HashMap<>();  					 	// pour sortir resultata et client 
		
	  	notificationServeur.sendNotification(client);
	
		String Pass = cryptaWithSHA256.cryptWithSHA256(password);
		
		client.setPassword(Pass);
		
		
		userRepository.save(client);
		
		result.put("Success add client have Registred ", client);
		
		  return result;

	    }
	
	
	
	public User ajouterAdmin(User admin) {
		
        Role role=null;
		
        admin.setRole(role.Admin);
      
       
		userRepository.save(admin);
		
		return admin;

	}
	
	
	
	public String authentification(String email, String password) throws Exception {
		
	
		Role role=null;
		
		List<User> users = (List<User>) userRepository.findAll();
		int verifyemail = 0;
		int verifypassword = 0;
		
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // verification mail
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(email);
		
		for (User user : users) {
			
			
			
			if (user.getEmail().equals(email)) {
				verifyemail++;
			 
				if (user.getPassword().equals(cryptaWithSHA256.cryptWithSHA256(password))) {
					
					verifypassword++;
					
					if(user.getRole()==role.Admin){
						 
						
						  try {
					            authenticationManager.authenticate(
					                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
					        } catch (Exception ex) {
					        	
					            throw new Exception("inavalid username/password ", ex);
					        }
						   
						return ("Welcome Admin : " + user.getUserName() + " " +  javautil.generateToken(user.getUserName()));
						
						
					}else if(user.getRole()==role.Client){
						
						if(user.isBlock()==true){
							
							return user.getDescriptionBlock() + "try to contact the admin !!";
							
						}else{
							
							
							  try {
						            authenticationManager.authenticate(
						                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
						            );
						        } catch (Exception ex) {
						            throw new Exception("inavalid username/password");
						        }
						 
							return ("Welcome Client : " + user.getUserName() + " " +javautil.generateToken(user.getUserName()));
						}
							
					} else if(user.getRole()==role.Visitor){
						
						return ("Welcome agent");
					}
					
				}else {   // else pa rapport satuts du password qui est incorrect 
					
					if(user.getRole()==role.Client){
						
						int nbre= user.getNbre();
						
						user.setNbre(nbre+1);
						
						userRepository.save(user);
						
						if(user.getNbre() == 3){
						
							user.setBlock(true);
							
							user.setDescriptionBlock("security problem");
							
							userRepository.save(user);
							
							return ("votre compte est blocke security problem");
							
						}else if (user.getNbre() >= 3){
							
							user.setNbre(3);
							
							userRepository.save(user);
							
							return ("Votre compte est blocke security problem");
							
						}else {
							return ("Password incorrect");
							
						}
						
					}
					
				}
			}else{
				
			if (!matcher.matches()) {
					
					return "Invalid email";
					
				}
				
			}
		}	
		if (verifyemail == 0) {
			
			return ("Email not found");
		} 
		else {
			
			return ("");
			
		}
					
	

	}
	
	
	
	
	private static boolean checkString(String str) {  // pour verifier  8 maj et min
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		boolean numberFlag = false;
		if (str.length() > 8) {
			for (int i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (Character.isDigit(ch)) {
					numberFlag = true;
				} else if (Character.isUpperCase(ch)) {
					capitalFlag = true;
				} else if (Character.isLowerCase(ch)) {
					lowerCaseFlag = true;
				}
				if (numberFlag && capitalFlag && lowerCaseFlag)
					return true;
			}
		}
		return false;
	}
	
	
	@Override
	public String forgetPassword(String email) {
		
		int existe=0;
		
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(email);
		
		if (!matcher.matches()) {
			return "invalid email";
		}
		
		List<User>users =(List<User>)userRepository.findAll();
		
		for(User us:users) {
			
			if(us.getEmail().equals(email)) {
				
				notificationServeur.sendNotification(us);
				
				
				existe=1;
				
				return "We sind you a new password pleaz try to connect with it";
				
			
			}
		}
		if(existe==0) {
			
			return "This compte n'existe pas you can create an accompte";
		}
		return null;
	}
	
	

	@Override
	public String deblockCompte(String email) {
		
		List<User> users = (List<User>) userRepository.findAll();
		
		for (User user : users) {
			
			System.out.println(user.getEmail());
			
			if (user.getEmail().equals(email)) {

				if (user.getDescriptionBlock().equals("security problem")) {
					
					notificationServeur.sendNotification(user);
					
					user.setDescriptionBlock("nothing");
					
					user.setNbre(0);
					
					user.setBlock(false);
					
					userRepository.save(user);
					
					return "we send you a new password pleaz chek your email";
					
				} else {
					
					return "this is not a security problem";
				}

			}
		}
		return "email not found";

	}
	
	
	@Override
	public String changerPassword(String username, String OldPassword,String password, String newPassword) {

		

		List<User> users = (List<User>) userRepository.findAll();
		
		for (User user : users) {
			
			if (user.getUserName().equals(username)) {

				if (user.getPassword().equals(cryptaWithSHA256.cryptWithSHA256(OldPassword))) {
					
					if(newPassword.equals(password)){
						
					
					if (checkString(newPassword)) {
						
						user.setPassword(cryptaWithSHA256.cryptWithSHA256(newPassword));
						
						userRepository.save(user);
						
						return "password changer with succes";
					} else {
						
						return "password have 8 caracter upper lower and number";
					}
					}else {
						return "the password not the same";
					}

				} else {
					return "password incorrect";
				}
			}
		}
		return ".";
	}

	
	public User getUserByUsername(String username){                          //teb3aa changer mdp pour recuperer utilisateur 
		
		User user=userRepository.findByUserName(username);
	
		return user;
	}
	

	public void SetPhotoByClient(String photo,Long idUser)
	{
		
		User user=userRepository.findById(idUser).get();
		
		user.setPicture(photo);
		
		userRepository.save(user);
	}
	
	
	

}
