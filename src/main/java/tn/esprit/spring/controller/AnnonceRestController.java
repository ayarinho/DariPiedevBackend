package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.services.IUannonceService;


@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnonceRestController {

	@Autowired
	
	IUannonceService annonceservice;
	
	@GetMapping("/getAdsByDistanceSorting/{idUser}")
	@ResponseBody
	public List<Ad> SortAnnonceByDistance(@PathVariable("idUser") long idUser) throws IOException, GeoIp2Exception, java.io.IOException{
		
		return annonceservice.SortAnnonceByDistance(idUser);
		
	}
	
	@GetMapping("/getDistance/{city}/{city1}")
	@ResponseBody
	public String distanceBetweenSellerAndBuyer(@PathVariable("city") String city,@PathVariable("city1") String city1) throws IOException, GeoIp2Exception, java.io.IOException{
		
		
	double distance =annonceservice.distanceBetweenSellerAndBuyer(city, city1);
	
	return  distance + " km";
	}
	
	
	@GetMapping("/getAllAds")
	@ResponseBody
	 public List<Ad> retrieveAllAds(){
		 
		 return annonceservice.retrieveAllAds();
	 }
	
	@DeleteMapping("/deleteAdById/{idAd}")
	@ResponseBody
	public String DeleteAd(@PathVariable("idAd") long idAd) {
		
		
		return annonceservice.DeleteAd(idAd);
	}
}
