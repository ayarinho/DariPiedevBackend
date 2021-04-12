package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import io.jsonwebtoken.io.IOException;
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.GeoIP;
import tn.esprit.spring.repository.AnnonceServiceRepository;
import tn.esprit.spring.repository.ConnectedUserRepository;

@Service

public class AnnonceServiceImpl implements IUannonceService {

	private static final Logger l = LogManager.getLogger(AnnonceServiceImpl.class);

	
	@Autowired
	ConnectedUserRepository connectedUserRepository;
	@Autowired
	tn.esprit.spring.repository.ReclamationRepository ReclamationRepository;

	@Autowired	
	
	GeoIPLocationService geoService;
	
	@Autowired
	
	AnnonceServiceRepository annonceRepository;

	
	public static double distance(double lat1, double lat2, double lon1, double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = 45000;   // hauteur   distance calculer selon latitde longitude et hauteur

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    
	    l.info("distanaaaaaaaaaaaaaaaaaaaaaaaaaaaaaance en kilometre  "+Math.sqrt(distance));
	    
	    return Math.sqrt(distance);
	}
	
	
	
	
	public double distanceBetweenSellerAndBuyer(String city,String city1) throws IOException, GeoIp2Exception, java.io.IOException{
		
		  
				GeoIP geo=	 geoService.findIpAddressByCityName(city);
				GeoIP geo1=	 geoService.findIpAddressByCityName(city1);
				
				double dLat1=Double.parseDouble(geo.getLatitude()); 
				double dLat2=Double.parseDouble(geo1.getLatitude()); 

				double dLong1=Double.parseDouble(geo.getLongitude()); 

				double dLong2=Double.parseDouble(geo1.getLongitude()); 

	
	       return distance(dLat1,dLat2,dLong1,dLong2);
	
		
	}
	
	public List<Ad> SortAnnonceByDistance(long idUser) throws IOException, GeoIp2Exception, java.io.IOException{
		
		 ConnectedUser user = connectedUserRepository.findById(idUser).get();
		
		 List<Ad> ads = (List<Ad>) annonceRepository.findAll();
		
		 double value=0;
		
		 TreeMap<Double, Ad> list= new TreeMap<Double, Ad>(); // treemap pour sorting
		 
		 List<Ad> adss= new ArrayList<>();

		 
		 for(Ad ad:ads){
			 
			  value=this.distanceBetweenSellerAndBuyer(user.getLieu(), ad.getLocation());
			  
			  
			 list.put(value, ad);
			 
			 
			 l.info("aaaaaaaaaaaaaa "+value);
		
		 }
		 for (Map.Entry mapentry : list.entrySet()) {
			 
	           System.out.println("clé: "+mapentry.getKey()   + " | valeur: " + mapentry.getValue());
	           
	          adss.add((Ad) mapentry.getValue());
	       	
	        }

         return adss;

	}
	
}
