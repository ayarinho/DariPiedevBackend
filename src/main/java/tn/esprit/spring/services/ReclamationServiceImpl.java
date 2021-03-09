package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.ReclamationRepository;

import tn.esprit.spring.repository.UserRepository;

@Service
public class ReclamationServiceImpl implements ReclamationService {

	@Autowired
	ReclamationRepository reclamationRepository;
	
	@Autowired
	MailService mailservice;
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	Filter filter;
	
	@Autowired
	CommentRepository  commentrepository;

	private static final Logger l = LogManager.getLogger(ReclamationServiceImpl.class);


	@Override
	public void addReclamation(Reclamation r) {
		reclamationRepository.save(r);
	}

	@Override
	public List<Reclamation> retrieveAllReclamations() {
		return (List<Reclamation>) reclamationRepository.findAll();
	}


	@Override
	public void deleteReclamation(long id) {
		reclamationRepository.deleteById((int) id);
	}
	
	
	
	@Override
	public void ajouterReclamation(Reclamation reclamation) {

		reclamationRepository.save(reclamation);
			
		
		
	}
	
	@Override
	public Reclamation findReclamation(String id) {
		Reclamation reclama= new Reclamation();
		Long j = Long.parseLong(id);
		List<Reclamation> reclamations =(List<Reclamation>) reclamationRepository.findAll();
		for(Reclamation reclamation:reclamations) {
			if(reclamation.getId()==(j)) {
				l.info("user ++:"+reclamation);
				reclama=reclamation;
			}
		}
		return reclama;
	}
	
	
	@Override
	public List<Reclamation> afficherReclamation() {
		List<Reclamation> Reclamations =(List<Reclamation>) reclamationRepository.findAll();
		for(Reclamation reclamation:Reclamations) {
			l.info("Reclamation ++:"+reclamation);
		}
		return Reclamations;
	}
	
	
	@Override
	public void affecterreclamationUser(String idReclamation, String IdUser) {
		User UserManageEntity = userRepository.findById(Long.parseLong(IdUser)).get();
		Reclamation ReclamationmanagerEntity= reclamationRepository.findById((int) Long.parseLong(idReclamation)).get();
		ReclamationmanagerEntity.setUserId(UserManageEntity);
		
		
		
		reclamationRepository.save(ReclamationmanagerEntity);
		
	}
	
	
	
	
	@Override
	public void ajouterReclamationuser(Reclamation reclamation, Long IdUser) {
		/*int x = 0;
		
		reclamationRepository.save(reclamation);
		
		long idReclamation=reclamation.getId();
		
		User UserManageEntity = userRepository.findById(IdUser).get();
		
		Reclamation ReclamationmanagerEntity= reclamationRepository.findById((int) idReclamation).get();
		
		ReclamationmanagerEntity.setUserId(UserManageEntity);
		
		reclamationRepository.save(ReclamationmanagerEntity);
		
		List<Reclamation> Reclamations =(List<Reclamation>) reclamationRepository.findAll();
		
		for(Reclamation recc:Reclamations) {
			
			if(recc.getUserId().getId()==IdUser) {
				x=x+1;
			}
		}
		
		User blockeduser = userRepository.findById(IdUser).get();
		
		List<Comment> comments =(List<Comment>) commentrepository.findAll();
		
		
		
		if(x>=3) {
			
			
			blockeduser.setBlock(true);
			
			blockeduser.setDescriptionBlock("you have 3 reclamation try to connect with the administration");
			
			userRepository.save(blockeduser);
			
		}*/
		
	}
	
	
	
	
	/*public Comment AddComment(Comment comment){
		
		return commentrepository.save(comment);
	}*/
	
	
	
	public void AddReclamationAndBlokUserWhenCommentsContainsBadWords(String iduser , String idComment) {
		
		int x=0;
	
		
		List<Reclamation> Reclamations =(List<Reclamation>) reclamationRepository.findAll();
		
	
		User userId = userRepository.findById(Long.parseLong(iduser)).get();
		
		Comment commentId = commentrepository.findById(Long.parseLong(idComment)).get();
		
			String output =filter.getCensoredText(commentId.getDescriptionComment());
			
		
			
			if(output.contains("*")){
				
				
				l.info("badwordsss   " + output);
				
				Reclamation reclamation= new Reclamation("user post in comments bad words !! ", userId, commentId);
				
				reclamationRepository.save(reclamation);

				 for(Reclamation recc:Reclamations) {
						
						if(recc.getUserId().getId()==Long.parseLong(iduser)) {
							
							x=x+1;
							
							l.info("xxxxxxx   " + x);
							
							
						}
					}
					
					User blockeduser = userRepository.findById(Long.parseLong(iduser)).get();
					
					Comment blockedComment = commentrepository.findById(Long.parseLong(idComment)).get();
					
				
					if(x>=3) {
						
						
						blockeduser.setBlock(true);
						
						blockeduser.setDescriptionBlock("you have 3 reclamation try to connect with the administration");
						
						mailservice.sendEmail(blockeduser.getEmail(), "your account", blockeduser.getUserName()); 
						
						blockedComment.setIsBlocked(true);
						
						blockedComment.setDescriptionComment(output);
						
						commentrepository.save(blockedComment);
						
						userRepository.save(blockeduser);
						
					}
					
					
	
			}
	
	}
	
		
	

	@Override
	public void deleteReclamation(Long id) {
		// TODO Auto-generated method stub
		
	}



	
	
}

