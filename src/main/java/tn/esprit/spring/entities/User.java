package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="User")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	
	private Long id;
	private String FirstName;
	private String LastName;
    private String Email;
	private String Password;
	private boolean block;
	private String DescriptionBlock;
	private int nbre;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String phoneNumber;
	private String picture;
	private String userName;
	@Enumerated
	private Role role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userId")
	private Set<Reclamation> Reclamation;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userId")
	private Set<Comment> comments;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String userName, String password, String email) {
		super();
		this.userName = userName;
		this.Password = password;
		this.Email = email;
	}
    

	public User(String userName, String password, String email,Role role) {
		super();
		this.userName = userName;
		this.Password = password;
		this.Email = email;
	}
    


	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}




	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}



	public String getLastName() {
		return LastName;
	}



	public void setLastName(String lastName) {
		LastName = lastName;
	}



	public String getEmail() {
		return Email;
	}






	public void setEmail(String email) {
		Email = email;
	}






	public String getPassword() {
		return Password;
	}






	public void setPassword(String password) {
		Password = password;
	}






	public boolean isBlock() {
		return block;
	}






	public void setBlock(boolean block) {
		this.block = block;
	}






	public String getDescriptionBlock() {
		return DescriptionBlock;
	}






	public void setDescriptionBlock(String descriptionBlock) {
		DescriptionBlock = descriptionBlock;
	}






	public int getNbre() {
		return nbre;
	}






	public void setNbre(int nbre) {
		this.nbre = nbre;
	}






	public Date getDateNaissance() {
		return dateNaissance;
	}






	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}






	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}




	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}