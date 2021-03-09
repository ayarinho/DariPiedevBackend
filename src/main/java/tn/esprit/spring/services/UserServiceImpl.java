package tn.esprit.spring.services;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
	

    /*public static final String ACCOUNT_SID = "AC372041b706c51a3b578ab2df3113de4e";
    public static final String AUTH_TOKEN = "c5efe5ea1c4280bed82cc14e7474aa49";
    
    Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
	
	 Message message = Message.creator(new PhoneNumber("+21627775151"),
			 new PhoneNumber("+17738048941"),"Bonjouuur").create();*/

	  
   
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CryptWithMD5  cryptaWithLD5;
	
	@Autowired
	NotificationServeur notificationServeur;
	
	@Autowired
	Filter filter;

    @Autowired
    JwtUtil javautil ;
   
   @Autowired
    AuthenticationManager authenticationManager;
	
	
	public User ajouterClient(User client,String password) {

		Role role=null;
		
		client.setRole(role.Client);
		
		notificationServeur.sendNotification(client);
		
		String Password = cryptaWithLD5.cryptWithMD5(password);
       
		client.setPassword(Password);
		
		 
		userRepository.save(client);
		
		return client;

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
		
		for (User user : users) {
			
			if (user.getEmail().equals(email)) {
				verifyemail++;
			 
				

				if (user.getPassword().equals(cryptaWithLD5.cryptWithMD5(password))) {
					
					verifypassword++;
					
					if(user.getRole()==role.Admin){
						 
						
						  try {
					            authenticationManager.authenticate(
					                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
					            );
					        } catch (Exception ex) {
					            throw new Exception("inavalid username/password");
					        }
						   
					         
						
						return ("Welcome Admin : " + user.getUserName() + " " +  javautil.generateToken(user.getUserName()));
						
					}else if(user.getRole()==role.Client){
						
						if(user.isBlock()==true){
							
							return user.getDescriptionBlock();
						}else{
							
							
							  try {
						            authenticationManager.authenticate(
						                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
						            );
						        } catch (Exception ex) {
						            throw new Exception("inavalid username/password");
						        }
						 
							return ("Welcome Client :" + user.getUserName()+" "+javautil.generateToken(user.getUserName()));
						}
							
					} else if(user.getRole()==role.Visitor){
						
						return ("Welcome agent");
					}
					
				}else {
					
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
							
							return ("votre compte est blocke security problem");
							
						}else {
							return ("password incorrect");
							
						}
						
					}
					
				}
			}
		}	
		if (verifyemail == 0) {
			return ("email not found ");
		} else {
			return ("");
		}
					
	

	}
	
	
	
	
	private static boolean checkString(String str) {
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
				
				return "we sind you a new password pleaz try to connect with it";
				
			
			}
		}
		if(existe==0) {
			
			return"this compte n'existe pas you can create an accompte";
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
					
					String password = cryptaWithLD5.cryptWithMD5(user.getPassword());

					user.setPassword(password);
					
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
	public String changerPassword(String id, String OldPassword,String password, String newPassword) {

		Long j = Long.parseLong(id);

		List<User> users = (List<User>) userRepository.findAll();
		
		for (User user : users) {
			
			if (user.getId().equals(j)) {

				if (user.getPassword().equals(cryptaWithLD5.cryptWithMD5(OldPassword))) {
					
					if(newPassword.equals(password)){
						
					
					if (checkString(newPassword)) {
						
						user.setPassword(cryptaWithLD5.cryptWithMD5(newPassword));
						
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


}
