package tn.esprit.spring;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import ch.qos.logback.classic.Level;
import tn.esprit.spring.audit.AuditorAwareImpl;
import tn.esprit.spring.services.UserServiceImpl;




@SpringBootApplication
@Configuration
@ServletComponentScan
public class PiedevDariApplication {

	
	private final static String ACCOUNT_SID = "AC21643d63563611a7b7bcda04e1260f44";
	private final static String AUTH_ID = "9f4281eaedf864c301f0846e78c9c0a0";
	private final static String FROM_NUMBER = "c5efe5ea1c4280bed82cc14e7474aa49";
	private final static String TO_NUMBER = "c5efe5ea1c4280bed82cc14e7474aa49";
	
	public static final Logger Logger=LogManager.getLogger(PiedevDariApplication.class);


	public static void main(String[] args) throws IOException, Exception {
		SpringApplication.run(PiedevDariApplication.class, args);
	
	        
} 
	

	}

	
	
	    
	
	
		


	
	
		
	
	
	
	
	
	
		
		 
		
	
	
	




