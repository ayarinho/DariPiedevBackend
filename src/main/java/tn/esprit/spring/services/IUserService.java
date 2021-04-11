package tn.esprit.spring.services;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.domain.Specification;

import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;

public interface IUserService {
	
	public Map<String, ConnectedUser>  ajouterClient(ConnectedUser client,String password) throws Exception;
	public String authentification(String email, String password) throws Exception;
	public String forgetPassword(String email);
	public String deblockCompte(String email);
	public String changerPassword(String id, String OldPassword,String password, String newPassword);
	//public User ajouterAdmin(User admin);
	public void SetPhotoByClient(String photo,Long idUser);
	public User getUserByUsername(String username);
	public String DeleteConnectUser(long idUser);
	public Specification<ConnectedUser> getAllUserByComments(long IdComment);
	public Specification<ConnectedUser> getUserByUsernameSpecifications(String fistname) ;

	public List<Object> fetchAllUserByUserName(String username);
	
}
