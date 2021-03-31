package tn.esprit.spring.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;



 @Repository
public interface  ConnectedUserRepository extends UserRepository<ConnectedUser,Long> {

	
	ConnectedUser findByUserName(String username);


}
