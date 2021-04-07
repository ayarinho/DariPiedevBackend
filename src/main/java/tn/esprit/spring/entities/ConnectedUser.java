package tn.esprit.spring.entities;

import java.io.Serializable;

import java.util.Date;
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


@AttributeOverrides({
    @AttributeOverride(name="FirstName", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="LastName", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="id", column=@Column(name="ID_USER"))
})

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ConnectedUser extends User  implements Serializable{
	

	
	private Date Today;
	private int nbrConnect;
	
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
	private Set<Reclamation> Reclamation;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="userId")
	private Set<Comment> comments;
	

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="user" )
	private Set<Appointment> Appointment;
	
	
	
	public ConnectedUser(Date today, int nbrConnect, Set<tn.esprit.spring.entities.Reclamation> reclamation,
			Set<Comment> comments, Set<tn.esprit.spring.entities.Appointment> appointment) {
		super();
		Today = today;
		this.nbrConnect = nbrConnect;
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
	public Date getToday() {
		return Today;
	}
	public void setToday(Date today) {
		Today = today;
	}
	public int getNbrConnect() {
		return nbrConnect;
	}
	public void setNbrConnect(int nbrConnect) {
		this.nbrConnect = nbrConnect;
	}
	public ConnectedUser( Set<Comment> comments,Date today, int nbrConnect) {
		super();
		Today = today;
		this.nbrConnect = nbrConnect;
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "ConnectedUser [Today=" + Today + ", nbrConnect=" + nbrConnect + ", Reclamation=" + Reclamation
				+ ", comments=" + comments + ", Appointment=" + Appointment + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", getReclamation()=" + getReclamation() + ", getComments()=" + getComments() + ", getAppointment()="
				+ getAppointment() + ", getToday()=" + getToday() + ", getNbrConnect()=" + getNbrConnect()
				+ ", getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", isBlock()=" + isBlock()
				+ ", getDescriptionBlock()=" + getDescriptionBlock() + ", getNbre()=" + getNbre()
				+ ", getDateNaissance()=" + getDateNaissance() + ", getPhoneNumber()=" + getPhoneNumber()
				+ ", getPicture()=" + getPicture() + ", getUserName()=" + getUserName() + ", getRole()=" + getRole()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getUpdatedBy()=" + getUpdatedBy() + ", getCreatedDate()="
				+ getCreatedDate() + ", getUpdatedDate()=" + getUpdatedDate() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	


}
