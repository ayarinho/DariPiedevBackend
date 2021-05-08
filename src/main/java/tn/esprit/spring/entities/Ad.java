package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_AD")


public class Ad implements Serializable{
	
		private static final long serialVersionUID = 1L;
        
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long IdAd;
		private int nbLikes;
		private int nbDisLikes;
		private String Description;
		private String Image;
		private String Location;
		private int Area;
		
		@Temporal(TemporalType.DATE)
		private Date AdDate;
		@Column(name = "ViewsNumber")
		private int ViewsNumber;
		private Boolean Success;
		@Column(name = "Score")
		private int Score;
		@Enumerated(EnumType.STRING)
	
		
		
		@Temporal(TemporalType.DATE)
		private Date StartDate;
		@Temporal(TemporalType.DATE)
		private Date EndDate;
		
		private float Price;
	
		@OneToMany(mappedBy = "Ads",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		private List<Comment> comments;
		
		@JoinColumn(name="user_idAd")
		@JsonIgnore
		@ManyToOne(fetch=FetchType.LAZY)
		ConnectedUser user;

		@OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "ad",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		private Set<Appointment> Appointment;
		
	
				public Ad(long idAd, int nbLikes, int nbDisLikes, String description, String image, String location, int area,
				Date adDate, int viewsNumber, Boolean success, int score, List<Comment> comments, Date startDate,
				Date endDate, float price, ConnectedUser user, Set<tn.esprit.spring.entities.Appointment> appointment,
				int nbBaths, int nbRooms, int nbGarage, boolean terrace, boolean swimmingPool, boolean garden,
				boolean airConditioning, boolean heater, boolean sousSol, boolean ascenseur, boolean furnished) {
			super();
			IdAd = idAd;
			this.nbLikes = nbLikes;
			this.nbDisLikes = nbDisLikes;
			Description = description;
			Image = image;
			Location = location;
			Area = area;
			AdDate = adDate;
			ViewsNumber = viewsNumber;
			Success = success;
			Score = score;
			this.comments = comments;
			StartDate = startDate;
			EndDate = endDate;
			Price = price;
			this.user = user;
			Appointment = appointment;
			this.nbBaths = nbBaths;
			this.nbRooms = nbRooms;
			this.nbGarage = nbGarage;
			Terrace = terrace;
			SwimmingPool = swimmingPool;
			Garden = garden;
			AirConditioning = airConditioning;
			this.heater = heater;
			SousSol = sousSol;
			this.ascenseur = ascenseur;
			Furnished = furnished;
		}

				private int nbBaths;
				private int nbRooms;
				private int nbGarage;
				//options
				private boolean Terrace;
			
				private boolean SwimmingPool;
			
				private boolean Garden;	
				private boolean AirConditioning;
				private boolean heater;
				public boolean SousSol;		
				private boolean ascenseur;
				
				private boolean Furnished;// cet attribut uniquement pour les annonce de location c'est à dire meublé ou nn

				public Long getIdAd() {
					return IdAd;
				}

				public void setIdAd(Long idAd) {
					IdAd = idAd;
				}

				public int getNbLikes() {
					return nbLikes;
				}

				public void setNbLikes(int nbLikes) {
					this.nbLikes = nbLikes;
				}

				public int getNbDisLikes() {
					return nbDisLikes;
				}

				public void setNbDisLikes(int nbDisLikes) {
					this.nbDisLikes = nbDisLikes;
				}

				public String getDescription() {
					return Description;
				}

				public void setDescription(String description) {
					Description = description;
				}

				public String getImage() {
					return Image;
				}

				public void setImage(String image) {
					Image = image;
				}

				public String getLocation() {
					return Location;
				}

				public void setLocation(String location) {
					Location = location;
				}

				public int getArea() {
					return Area;
				}

				public void setArea(int area) {
					Area = area;
				}

				public Date getAdDate() {
					return AdDate;
				}

				public void setAdDate(Date adDate) {
					AdDate = adDate;
				}

				public int getViewsNumber() {
					return ViewsNumber;
				}

				public void setViewsNumber(int viewsNumber) {
					ViewsNumber = viewsNumber;
				}

				public Boolean getSuccess() {
					return Success;
				}

				public void setSuccess(Boolean success) {
					Success = success;
				}

				public int getScore() {
					return Score;
				}

				public void setScore(int score) {
					Score = score;
				}

				public List<Comment> getComments() {
					return comments;
				}

				public void setComments(List<Comment> comments) {
					this.comments = comments;
				}

				public ConnectedUser getUser() {
					return user;
				}

				public void setUser(ConnectedUser user) {
					this.user = user;
				}

				public Date getStartDate() {
					return StartDate;
				}

				public void setStartDate(Date startDate) {
					StartDate = startDate;
				}

				public Date getEndDate() {
					return EndDate;
				}

				public void setEndDate(Date endDate) {
					EndDate = endDate;
				}

				public float getPrice() {
					return Price;
				}

				public void setPrice(float price) {
					Price = price;
				}

				public Set<Appointment> getAppointment() {
					return Appointment;
				}

				public void setAppointment(Set<Appointment> appointment) {
					Appointment = appointment;
				}

				public int getNbBaths() {
					return nbBaths;
				}

				public void setNbBaths(int nbBaths) {
					this.nbBaths = nbBaths;
				}

				public int getNbRooms() {
					return nbRooms;
				}

				public void setNbRooms(int nbRooms) {
					this.nbRooms = nbRooms;
				}

				public int getNbGarage() {
					return nbGarage;
				}

				public void setNbGarage(int nbGarage) {
					this.nbGarage = nbGarage;
				}

				public boolean isTerrace() {
					return Terrace;
				}

				public void setTerrace(boolean terrace) {
					Terrace = terrace;
				}

				public boolean isSwimmingPool() {
					return SwimmingPool;
				}

				public void setSwimmingPool(boolean swimmingPool) {
					SwimmingPool = swimmingPool;
				}

				public boolean isGarden() {
					return Garden;
				}

				public void setGarden(boolean garden) {
					Garden = garden;
				}

				public boolean isAirConditioning() {
					return AirConditioning;
				}

				public void setAirConditioning(boolean airConditioning) {
					AirConditioning = airConditioning;
				}

				public boolean isHeater() {
					return heater;
				}

				public void setHeater(boolean heater) {
					this.heater = heater;
				}

				public boolean isSousSol() {
					return SousSol;
				}

				public void setSousSol(boolean sousSol) {
					SousSol = sousSol;
				}

				public boolean isAscenseur() {
					return ascenseur;
				}

				public void setAscenseur(boolean ascenseur) {
					this.ascenseur = ascenseur;
				}

				public boolean isFurnished() {
					return Furnished;
				}

				public void setFurnished(boolean furnished) {
					Furnished = furnished;
				}

				public static long getSerialversionuid() {
					return serialVersionUID;
				}

				public Ad(long idAd, int nbLikes, int nbDisLikes, String description, String image, String location,
						int area, Date adDate, int viewsNumber, Boolean success, int score, List<Comment> comments,
						ConnectedUser user, Date startDate, Date endDate, float price,
						Set<tn.esprit.spring.entities.Appointment> appointment, int nbBaths, int nbRooms, int nbGarage,
						boolean terrace, boolean swimmingPool, boolean garden, boolean airConditioning, boolean heater,
						boolean sousSol, boolean ascenseur, boolean furnished) {
					super();
					IdAd = idAd;
					this.nbLikes = nbLikes;
					this.nbDisLikes = nbDisLikes;
					Description = description;
					Image = image;
					Location = location;
					Area = area;
					AdDate = adDate;
					ViewsNumber = viewsNumber;
					Success = success;
					Score = score;
					this.comments = comments;
					this.user = user;
					StartDate = startDate;
					EndDate = endDate;
					Price = price;
					Appointment = appointment;
					this.nbBaths = nbBaths;
					this.nbRooms = nbRooms;
					this.nbGarage = nbGarage;
					Terrace = terrace;
					SwimmingPool = swimmingPool;
					Garden = garden;
					AirConditioning = airConditioning;
					this.heater = heater;
					SousSol = sousSol;
					this.ascenseur = ascenseur;
					Furnished = furnished;
				}

				public Ad() {
					super();
					// TODO Auto-generated constructor stub
				}

				@Override
				public String toString() {
					return "Ad [IdAd=" + IdAd + ", nbLikes=" + nbLikes + ", nbDisLikes=" + nbDisLikes + ", Description="
							+ Description + ", Image=" + Image + ", Location=" + Location + ", Area=" + Area
							+ ", AdDate=" + AdDate + ", ViewsNumber=" + ViewsNumber + ", Success=" + Success
							+ ", Score=" + Score + ", comments=" + comments + ", user=" + user + ", StartDate="
							+ StartDate + ", EndDate=" + EndDate + ", Price=" + Price + ", Appointment=" + Appointment
							+ ", nbBaths=" + nbBaths + ", nbRooms=" + nbRooms + ", nbGarage=" + nbGarage + ", Terrace="
							+ Terrace + ", SwimmingPool=" + SwimmingPool + ", Garden=" + Garden + ", AirConditioning="
							+ AirConditioning + ", heater=" + heater + ", SousSol=" + SousSol + ", ascenseur="
							+ ascenseur + ", Furnished=" + Furnished + "]";
				}
				
				
	
		
		
}
