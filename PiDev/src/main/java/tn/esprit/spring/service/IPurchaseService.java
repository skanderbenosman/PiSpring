package tn.esprit.spring.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Purchase;

public interface IPurchaseService {

	List<Purchase> retrieveAllPurchase(); 

	String addPurchase(Purchase u);

	void deletePurchase(int i);

	Purchase updateContrat(Purchase u);

	Purchase retrievePurchase(String id);
	
	@Query("Select m.contrat FROM Purchase m WHERE m.id = :id")
	  public Contrat findContratByPurchase(@Param("id") Long id);
	
	
	  public Contrat updateContratByPurchase(Long id);
	  

	  public List<Purchase> SearchP(String word);
	  
	 
	  public List<Purchase> recupérePurchaseByUser(Long id);
}
