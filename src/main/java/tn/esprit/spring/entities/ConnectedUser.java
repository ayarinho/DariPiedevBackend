package tn.esprit.spring.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;


@AttributeOverrides({
    @AttributeOverride(name="FirstName", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="LastName", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="id", column=@Column(name="ID_USER"))
})

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ConnectedUser extends User  implements Serializable{
	

	private String lieu;
	
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
	private Set<Reclamation> Reclamation;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
	private Set<Comment> comments;
	

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="user" )
	private Set<Appointment> Appointment;
	
	@OneToMany( mappedBy="Abonnes" )
	private List<Abonnement> abonnement;
	

	/*@OneToMany(mappedBy="user")
	private List<Ad> ads;*/
	
	
	

	
	public ConnectedUser( Set<tn.esprit.spring.entities.Reclamation> reclamation,
			Set<Comment> comments, Set<tn.esprit.spring.entities.Appointment> appointment) {
		super();
		
		Reclamation = reclamation;
		this.comments = comments;
		Appointment = appointment;
	}
	public Set<Reclamation> getReclamation() {
		return Reclamation;
	}
	public void setReclamation(Set<Reclamation> reclamation) {
		Reclamation = reclamation;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Appointment> getAppointment() {
		return Appointment;
	}
	public void setAppointment(Set<Appointment> appointment) {
		Appointment = appointment;
	}
	public ConnectedUser(String userName, String password, String email, Role role) {
		super(userName, password, email, role);
		// TODO Auto-generated constructor stub
	}
	public ConnectedUser(String userName, String password, String email) {
		super(userName, password, email);
		// TODO Auto-generated constructor stub
	}
	public ConnectedUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public List<Abonnement> getAbonnement() {
		return abonnement;
	}
	public void setAbonnement(List<Abonnement> abonnement) {
		this.abonnement = abonnement;
	}
	@Override
	public String toString() {
		return "ConnectedUser [lieu=" + lieu + ", Reclamation=" + Reclamation + ", comments=" + comments
				+ ", Appointment=" + Appointment + ", abonnement=" + abonnement + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", getReclamation()=" + getReclamation() + ", getComments()=" + getComments() + ", getAppointment()="
				+ getAppointment() + ", getLieu()=" + getLieu() + ", getAbonnement()=" + getAbonnement() + ", getId()="
				+ getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getPassword()=" + getPassword() + ", isBlock()=" + isBlock()
				+ ", getDescriptionBlock()=" + getDescriptionBlock() + ", getNbre()=" + getNbre()
				+ ", getDateNaissance()=" + getDateNaissance() + ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getPicture()=" + getPicture() + ", getUserName()=" + getUserName() + ", getRole()=" + getRole()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getUpdatedBy()=" + getUpdatedBy() + ", getCreatedDate()="
				+ getCreatedDate() + ", getUpdatedDate()=" + getUpdatedDate() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public ConnectedUser(String lieu, Set<tn.esprit.spring.entities.Reclamation> reclamation, Set<Comment> comments,
			Set<tn.esprit.spring.entities.Appointment> appointment, List<Abonnement> abonnement) {
		super();
		this.lieu = lieu;
		Reclamation = reclamation;
		this.comments = comments;
		Appointment = appointment;
		this.abonnement = abonnement;
	}
	
	
	
	


}
