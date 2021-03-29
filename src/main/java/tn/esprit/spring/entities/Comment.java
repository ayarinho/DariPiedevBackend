package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "T_COMMENT")
public class Comment implements Serializable{

			private static final long serialVersionUID = 1L;

			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private Long IdComment;
			
			private String DescriptionComment;
			private int NumberLikes;
			
			private Boolean IsBlocked;
		
			//@JsonIgnore
			@ManyToOne(cascade = CascadeType.ALL) 
			User userId;
		    
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="commentaire")
			private Set<Reclamation> reclamations;

			public Long getIdComment() {
				return IdComment;
			}

			public void setIdComment(Long idComment) {
				IdComment = idComment;
			}

			public String getDescriptionComment() {
				return DescriptionComment;
			}

			public void setDescriptionComment(String descriptionComment) {
				DescriptionComment = descriptionComment;
			}

			public int getNumberLikes() {
				return NumberLikes;
			}

			public void setNumberLikes(int numberLikes) {
				NumberLikes = numberLikes;
			}

			public Boolean getIsBlocked() {
				return IsBlocked;
			}

			public void setIsBlocked(Boolean isBlocked) {
				IsBlocked = isBlocked;
			}

			public Set<Reclamation> getReclamations() {
				return reclamations;
			}

			public void setReclamations(Set<Reclamation> reclamations) {
				this.reclamations = reclamations;
			}

			public Comment(Long idComment, String descriptionComment, int numberLikes, Boolean isBlocked, User userId,
					Set<Reclamation> reclamations) {
				super();
				IdComment = idComment;
				DescriptionComment = descriptionComment;
				NumberLikes = numberLikes;
				IsBlocked = isBlocked;
				this.userId = userId;
				this.reclamations = reclamations;
			}

			public Comment() {
				super();
				// TODO Auto-generated constructor stub
			}

			public User getUserId() {
				return userId;
			}

			public void setUserId(User userId) {
				this.userId = userId;
			}

		
			

}