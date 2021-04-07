package tn.esprit.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import tn.esprit.spring.entities.ConnectedUser;


@Component
public class ConnectedUserSpec {      /// exposer directement fel Restcontroller

	
	public static Specification<ConnectedUser> hasFirstName(String fistname){
		
		return((root,criteriaQuery,criteriaBuilder)->{
			
			return criteriaBuilder.equal(root.get("FirstName"),fistname);
		});
	}
	
}