package tn.esprit.spring.entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/*@Entity

@AttributeOverrides({
    @AttributeOverride(name="FirstName", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="LastName", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="id", column=@Column(name="ID"))
})
public class ConnectedUser extends User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//private Long Id;
	
	private Date Today;
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
	private int nbrConnect;
	
	


}*/
