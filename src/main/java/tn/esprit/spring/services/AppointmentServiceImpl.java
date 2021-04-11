package tn.esprit.spring.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import io.jsonwebtoken.lang.Collections;
import tn.esprit.spring.controller.UserRestController;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.GeoIP;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.repository.AppointmentRepository;

import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.GeoIpRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	AppointmentRepository AppointmentRepository;
	@Autowired
	ConnectedUserRepository connectedUserRepository;
	@Autowired
	tn.esprit.spring.repository.ReclamationRepository ReclamationRepository;


	
	
	private static final Logger l = LogManager.getLogger(AppointmentServiceImpl.class);

	@Override
	public List<Appointment> retrieveAllAppointment() {
		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();

		return app;
	}

	@Override
	public synchronized String addAppointment(Appointment a, long idUser) { 
		
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		String DateSys = dateFormat.format(calendar.getTime());
		String strDate = dateFormat.format(a.getDateAppointement());

		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();

		ConnectedUser user = connectedUserRepository.findById(idUser).get();
		
		
		


		if (app.isEmpty()) {

			a.setUser(user);
			AppointmentRepository.save(a);

		} else {

			boolean verifey = false;

			for (Appointment ap : app) {

				if (a.getDateAppointement().getHours() == ap.getDateAppointement().getHours()) {

					verifey = true;

					return "There is an appointement in this hour";
				}

				if (strDate.compareTo(DateSys) < 0) {

					verifey = true;

					return "Date invalid is less than system date";
				}
			}

			if (!verifey) {

				a.setUser(user);

				AppointmentRepository.save(a);

				return "Appointement added with success";
			}

		}
		return "Nothing";
	}

	@Override
	public void deleteAppointment(long idUser) {

		AppointmentRepository.deleteById(idUser);

	}

	@Override
	public List<Appointment> getApp() {

		return (List<Appointment>) AppointmentRepository.findAll();
	}

	@Override
	public String CancledAppointment(Date date, long idUser) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		Calendar calendar = Calendar.getInstance();

		String DateSys = dateFormat.format(calendar.getTime());
		String strDate = dateFormat.format(date);

		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		ConnectedUser user = connectedUserRepository.findById(idUser).get();

		for (Appointment a : app) {

			if (a.getDateAppointement().toString().equals(strDate) && strDate.compareTo(DateSys) > 0
					&& a.getUser() == user && a.getState() != "Canceled") {

				a.setState("Canceled");
				a.setAttendance("Absent");
				a.setJustification("Nothing"); // enumeration badelhaaa

				AppointmentRepository.save(a);

				return "Cancel Appointement succesed";

			}

		}

		return "Try to put the right date of your appointement";

	}

	@Override
	public String ConfrmerAppointment(Date date, long idUser) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		Calendar calendar = Calendar.getInstance();

		String strDate = dateFormat.format(date);
		String DateSys = dateFormat.format(calendar.getTime());

		List<Appointment> app = (List<Appointment>) AppointmentRepository.findAll();
		ConnectedUser user = connectedUserRepository.findById(idUser).get();

		for (Appointment a : app) {

			if (a.getDateAppointement().toString().equals(strDate) && a.getUser() == user
					&& strDate.compareTo(DateSys) > 0 && a.getState() != "Confirmed") {

				a.setState("Confirmed");
				a.setAttendance("Present");
				a.setJustification("Nothing"); // enumeration badelhaaa

				AppointmentRepository.save(a);

				return "Appointement is confirmed";

			}

		}

		return "Try to put the right date of your appointement";
	}
	
	
	
	

}
