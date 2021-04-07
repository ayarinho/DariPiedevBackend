package tn.esprit.spring.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.security.core.context.SecurityContextHolder;

import tn.esprit.spring.controller.ReclamationRestController;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.ReclamationRepository;
import tn.esprit.spring.services.AppointmentService;
import tn.esprit.spring.services.Filter;

@Configuration
@EnableScheduling
public class ScheduledTaskExample implements ApplicationContextAware, BeanNameAware {

	private static final Logger logger = Logger.getLogger(ScheduledTaskExample.class);

	
	
	@Autowired

	CommentRepository commentRepository;

	@Autowired
	Filter filter;

	@Autowired
	ReclamationRepository reclamationRepository;

	@Autowired
	AppointmentRepository appointementRepository;

	@Autowired
	ConnectedUserRepository connectedRepository;

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	AppointmentRepository appointmentRepository;

	private ApplicationContext applicationContext;
	private String beanName;

	@Scheduled(fixedDelay = 30000)
	@Async
	public void someTask() { // alkhater base twali ferghaa beleeek

		try {

			List<Appointment> apps = (List<Appointment>) appointementRepository.findAll();

			List<ConnectedUser> users = (List<ConnectedUser>) connectedRepository.findAll();
			int x = 0;
			ConnectedUser User = null;

			List<Appointment> temp = new ArrayList<>();

			for (ConnectedUser user : users) {

				for (Appointment ap : apps) {

					if (ap.getUser().getId().equals(user.getId()) && ap.getState().equals("Canceled")) {

						x = x + 1;

						temp.add(ap);

						if (x >= 2) {

							for (Appointment tp : temp) {

								User = tp.getUser();
	
								appointementRepository.delete(tp);
							
							}

						
						}

					}

				}

				x = 0;
				temp.clear();

			}
		} catch (NullPointerException e) {

			System.out.println("Database is empty there is no appointements " + e.getMessage());

			/*
			 * appointmentRepository.save(new Appointment((long) 1, new Date(),
			 * false, "Confirmed", 8, "present", false,
			 * "appointement for verification", new Date(),
			 * "ask admin for files", null));// ajout appointement bech base
			 * to93edch fergha
			 */

		}
	}

	private void stopScheduledTask() {
		ScheduledAnnotationBeanPostProcessor bean = applicationContext
				.getBean(ScheduledAnnotationBeanPostProcessor.class);
		bean.postProcessBeforeDestruction(this, beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
}