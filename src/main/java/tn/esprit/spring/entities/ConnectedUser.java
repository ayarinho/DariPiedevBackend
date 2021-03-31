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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


/*@Entity

@AttributeOverrides({
    @AttributeOverride(name="FirstName", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="LastName", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="id", column=@Column(name="ID"))
})*/

@Entity
public class ConnectedUser extends User  implements Serializable{
	

	
	private Date Today;
	private int nbrConnect;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userId")
	private Set<Reclamation> Reclamation;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="userId")
	private Set<Comment> comments;
	
	
	
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
	
	
	


}
