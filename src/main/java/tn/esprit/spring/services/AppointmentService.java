package tn.esprit.spring.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Appointment;


public interface AppointmentService {
	
	List<Appointment> retrieveAllAppointment();
	
	public String addAppointment(Appointment a,long idUser);

	void deleteAppointment(long id);
	
	public String ConfrmerAppointment(Date date , long idUser);

	public String CancledAppointment(Date date,long idUser);
	
	public List<Appointment> getApp();
	


}
