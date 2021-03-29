package tn.esprit.spring.services;

import java.util.Map;

import tn.esprit.spring.entities.User;

public interface IUserService {
	
	public Map<String, User>  ajouterClient(User client,String password) throws Exception;
	public String authentification(String email, String password) throws Exception;
	public String forgetPassword(String email);
	public String deblockCompte(String email);
	public String changerPassword(String id, String OldPassword,String password, String newPassword);
	public User ajouterAdmin(User admin);
	public void SetPhotoByClient(String photo,Long idUser);
	public User getUserByUsername(String username);

}
