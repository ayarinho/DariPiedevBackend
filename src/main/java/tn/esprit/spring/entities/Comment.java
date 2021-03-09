package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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
		
			@ManyToOne
			private User user;
		
			/*@ManyToOne
			private Ad Ads;
			*/
		
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


			public static long getSerialversionuid() {
				return serialVersionUID;
			}

			public Comment() {
				super();
				// TODO Auto-generated constructor stub
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}
	
	

}
