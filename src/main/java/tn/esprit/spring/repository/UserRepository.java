package tn.esprit.spring.repository;


import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;


@NoRepositoryBean
//@Repository
public interface  UserRepository<T, Long extends Serializable> extends JpaRepository<T, Long>{ // type generique bech eli bech heritiih meyouhelech kima list

	
	//T findByUserName(String username);


	
}
