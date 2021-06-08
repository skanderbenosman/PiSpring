package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

//	@Query("Select m FROM Contrat m join m.user r WHERE r.id = :id")
  //  public  Advertisement findByIdRole1(@Param("id") Long id);
	@Query("Select m.contrat FROM Purchase m WHERE m.id = :id")
	  public  Contrat findContratByPurchase(@Param("id") Long id);
	
	@Query(value="Update m.contrat.status set m.contrat.status='valid' WHERE m.id = :id",nativeQuery =true)
	public  Contrat UpdateContrat(@Param("id") Long id);
	
	//recherche de filtre par prix
	   
	   @Query(value="SELECT * from Purchase e where e.prix like %:word%",nativeQuery =true)	
	   public List<Purchase> searchPurchase(@Param("word") String word);
	   
		@Query("Select m FROM Purchase m join m.user r WHERE r.id = :id")
	    public List<Purchase> findPurchaseByUser(@Param("id") Long id);

}
