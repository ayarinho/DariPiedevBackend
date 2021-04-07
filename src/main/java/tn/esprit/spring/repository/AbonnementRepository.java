package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Abonnement;

import tn.esprit.spring.entities.User;


public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

	@Modifying 
	@Transactional
	@Query(value = "INSERT INTO `abonnement` (`idf`, `abonnes_id`, `abonnements_id`) VALUES (NULL, :Abonnes, :Abonnements)", nativeQuery = true)
	void insertAbonnes(@Param("Abonnes") Long Abonnes, @Param("Abonnements") Long Abonnements);

	
	@Query(value = "SELECT COUNT(*) FROM Abonnement WHERE abonnes_id_user=:Abonnes")
	
	public int nombreAbonnes(@Param("Abonnes") Long Abonnes);
	
	@Query(value = "SELECT idF FROM Abonnement WHERE abonnes_id_user=:Abonnes and abonnements_id_user=:Abonnements ")
	
	public Long getAbonnes(@Param("Abonnes") Long Abonnes,@Param("Abonnements") Long Abonnements);
	
	@Query(value = "SELECT count(*) FROM USER", nativeQuery = true)
	public int retrieveUsers();

	@Query( " from ConnectedUser U inner join Abonnement f ON   U.id=f.Abonnes and f.Abonnes=:Abonnes")
	public List<Object> MesAbonnes(@Param("Abonnes") Long Abonnes);

	@Query(value = "SELECT * FROM connected_user U inner JOIN abonnement f ON fabonnements_id_user=:Abonnements and U.id_user=f.abonnes_id_user  ", nativeQuery = true)
	public List<Object> Abonnements(@Param("Abonnements") Long Abonnements);

}
