package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Reclamation implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long id; 
	private String Description;
	
	@ManyToOne(cascade = CascadeType.ALL) 
	ConnectedUser userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Comment commentaire;


	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reclamation(Long id, String description) {
		super();
		this.id = id;
		Description = description;
	}
	public ConnectedUser getUserId() {
		return userId;
	}
	public void setUserId(ConnectedUser userId) {
		this.userId = userId;
	}
	public Comment getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(Comment commentaire) {
		this.commentaire = commentaire;
	}
	public Reclamation(String description, ConnectedUser userId, Comment commentaire) {
		super();
		Description = description;
		this.userId = userId;
		this.commentaire = commentaire;
	}
	

	
	
	
}
	