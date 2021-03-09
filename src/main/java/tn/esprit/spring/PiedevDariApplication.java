package tn.esprit.spring;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.IUserService;


@SpringBootApplication

public class PiedevDariApplication implements ApplicationRunner {

	private final static String ACCOUNT_SID = "AC372041b706c51a3b578ab2df3113de4e";
	private final static String AUTH_ID = "c5efe5ea1c4280bed82cc14e7474aa49";
	private final static String FROM_NUMBER = "c5efe5ea1c4280bed82cc14e7474aa49";
	private final static String TO_NUMBER = "c5efe5ea1c4280bed82cc14e7474aa49";
	
	public static final Logger L=LogManager.getLogger(PiedevDariApplication.class);

	 @Autowired
	    private UserRepository repository;

	    @PostConstruct
	    public void initUsers() {
	    	
	    
	    	
	        List<User> users = Stream.of(
	        		 new User( "javatechie", "781227", "youssef.ayari1@esprit.tn")   // houni enregistrement
	                
	        ).collect(Collectors.toList());
	        repository.saveAll(users);
	    }
	
	static{
		 
		

        Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	public static void main(String[] args) {
		SpringApplication.run(PiedevDariApplication.class, args);
		
		
		
		
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		

		
		
       Call call = Call.creator(
        		
        	     new com.twilio.type.PhoneNumber("+21626579007"),
                new com.twilio.type.PhoneNumber("+17738048941"),
           
                URI.create("http://demo.twilio.com/docs/voice.xml"))
            .create();



		
	}

	

}
