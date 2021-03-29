package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Abonnement implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	
	private Long idF;  
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL) 
	User Abonnes;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL) 
	User Abonnements;
	
	public Abonnement(Long id, User Abonnes, User Abonnements) {
		super();
		this.idF = id;
		Abonnes = Abonnes;
		Abonnements = Abonnements;
	}
	public Abonnement() {
		super();
	
	}
	@Override
	public String toString() {
		return "Follow [id=" + idF + ", Abonnes=" + Abonnes + ", Abonnements=" + Abonnements + "]";
	}
	public Long getId() {
		return idF;
	}
	public void setId(Long id) {
		this.idF = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Abonnes == null) ? 0 : Abonnes.hashCode());
		result = prime * result + ((Abonnements == null) ? 0 : Abonnements.hashCode());
		result = prime * result + ((idF == null) ? 0 : idF.hashCode());
		return result;
	}
	
	
	
	public Long getIdF() {
		return idF;
	}
	public void setIdF(Long idF) {
		this.idF = idF;
	}
	public User getAbonnes() {
		return Abonnes;
	}
	public void setAbonnes(User abonnes) {
		Abonnes = abonnes;
	}
	public User getAbonnements() {
		return Abonnements;
	}
	public void setAbonnements(User abonnements) {
		Abonnements = abonnements;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abonnement other = (Abonnement) obj;
		if (Abonnes == null) {
			if (other.Abonnes != null)
				return false;
		} else if (!Abonnes.equals(other.Abonnes))
			return false;
		if (Abonnements == null) {
			if (other.Abonnements != null)
				return false;
		} else if (!Abonnements.equals(other.Abonnements))
			return false;
		if (idF == null) {
			if (other.idF != null)
				return false;
		} else if (!idF.equals(other.idF))
			return false;
		return true;
	}
	
}
