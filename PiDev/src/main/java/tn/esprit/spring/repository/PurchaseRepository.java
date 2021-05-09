package tn.esprit.spring.repository;

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

}
