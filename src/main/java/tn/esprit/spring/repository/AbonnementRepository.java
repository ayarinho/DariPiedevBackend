package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Abonnement;

import tn.esprit.spring.entities.User;


public interface AbonnementRepository extends CrudRepository<Abonnement, Long> {

	@Modifying 
	@Transactional
	@Query(value = "INSERT INTO `abonnement` (`idf`, `abonnes_id`, `abonnements_id`) VALUES (NULL, :Abonnes, :Abonnements);", nativeQuery = true)
	void insertAbonnes(@Param("Abonnes") Long Abonnes, @Param("Abonnements") Long Abonnements);
	//

	/*@Query(value = "SELECT * FROM USER U inner JOIN follow f ON f.follow_id=11 and U.id=f.followed_id ", nativeQuery = true)
	public List<User> MyFollow();*/
	
	@Query(value = "SELECT COUNT(*) FROM abonnement WHERE `abonnes_id`=:Abonnes", nativeQuery = true)
	
	public int nombreAbonnes(@Param("Abonnes") Long Abonnes);
	
	
	@Query(value = "SELECT `idf` FROM `abonnement` WHERE `abonnes_id`=:Abonnes and `abonnements_id`=:Abonnements ", nativeQuery = true)
	
	public Long getAbonnes(@Param("Abonnes") Long Abonnes,@Param("Abonnements") Long Abonnements);
	
	
	
	/*@Modifying
	@Transactional
	@Query(value = "DELETE from follow WHERE idf = :id ", nativeQuery = true)
	public void deleteIdd(@Param("id") Long id);*/
	

	@Query(value = "SELECT count(*) FROM USER", nativeQuery = true)
	public int retrieveUsers();

	@Query(nativeQuery = true , value = "SELECT * FROM USER U inner JOIN abonnement f ON f.abonnes_id=:Abonnes and U.id=f.abonnements_id ")
	public List<Object> MesAbonnes(@Param("Abonnes") Long Abonnes);

	@Query(value = "SELECT * FROM USER U inner JOIN abonnement f ON f.abonnements_id=:Abonnements and U.id=f.abonnes_id  ", nativeQuery = true)
	public List<Object> Abonnements(@Param("Abonnements") Long Abonnements);

	/*@Query(value ="SELECT * FROM `user` U INNER JOIN follow F on f.follow_id=:userBlocker AND U.`id`=f.followed_id ORDER BY RAND() LIMIT 2", nativeQuery = true)
	public List<User> whofollowyou(@Param("userBlocker") Long userBlocker);
	
	
	@Query(value ="SELECT * FROM USER U inner JOIN follow f ON f.follow_id<>:userBlocker and U.id=f.follow_id  ORDER BY RAND() LIMIT 3", nativeQuery = true)
	public List<User> whofollowdontyou(@Param("userBlocker") Long userBlocker);*/
	
}
