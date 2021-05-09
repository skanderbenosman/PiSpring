package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.User;


@Repository
public interface ContratRepository extends CrudRepository<Contrat, Long>{

 
	@Query("Select m FROM Advertisement m join m.user r WHERE r.id = :id")
    public  Advertisement findAdevertisement1ByUser(@Param("id") Long id);
	@Query("Select m FROM Contrat m join m.advertisement r WHERE r.user.id = :id")
	  public  List<Contrat> findContratByUsername(@Param("id") Long id);
	@Query("Select m.advertisement FROM Contrat m WHERE m.id = :id")
	  public  Advertisement findAdvertisementByContrat(@Param("id") Long id);
	@Query("Select m.price FROM Advertisement m WHERE m.id = :id")
	  public  float findPriceByAdvertisement(@Param("id") Long id);
	@Query("Select m.contrat FROM Purchase m WHERE m.id = :id")
	  public  Contrat findContratByPurchase(@Param("id") Long id);
	@Modifying
	@Transactional
	@Query
    ("UPDATE Contrat e SET e.status=:status, e.titre=:titre , e.description=:description ,e.dateS=:dateS where e.id=:id")
	public void majname
	(@Param("status")String status,
	@Param("titre")String titre,
	@Param("description")String description,
	@Param("dateS")Date dateS,
	@Param("id")Long id);
	
	@Modifying
	@Transactional
	@Query
    ("UPDATE Contrat e SET e.status=:status  where e.id=:id")
	public void updateContrat(@Param("status")String status,@Param("id")Long id);

	   @Query("Select m FROM Contrat m WHERE m.UserName = :UserName")
		  public List<Contrat> findContratParUtilisateur(@Param("UserName") String UserName);
	   @Query("Select m FROM Contrat m WHERE m.UserName = :UserName AND m.status =:status")
		  public List<Contrat> findContratParUtilisateurS(@Param("UserName") String UserName,@Param("status") String status);
	   
	   @Query("Select m FROM Contrat m join m.advertisement r WHERE r.id = :id")
		  public  Contrat findContratByAdv(@Param("id") Long id);
}
