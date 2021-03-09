package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Reclamation;


public interface ReclamationRepository extends CrudRepository<Reclamation, Integer>{
	//"
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM `reclamation` WHERE `reclamation`.`id` = :id", nativeQuery = true)
	public void Delete(@Param("id") Long id);
}
