package tn.esprit.spring.services;

import java.util.List;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import io.jsonwebtoken.io.IOException;
import tn.esprit.spring.entities.Ad;

public interface IUannonceService {

	
	public double distanceWIthSellerAndBuyer(String city,String city1) throws IOException, GeoIp2Exception, java.io.IOException;
	
	public List<Ad> SortAnnonceByDistance(long idUser) throws IOException, GeoIp2Exception, java.io.IOException;
}
