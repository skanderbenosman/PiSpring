package tn.esprit.spring.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Contrat;


public interface IContratService {

	List<Contrat> retrieveAllContrat(); 

	Contrat addContrat(Contrat u);

	void deleteContrat(int i);

	void updateContrat(Contrat u);

	Contrat retrieveContrat(String id);
	
	@Query("Select m.address FROM Advertisement m join m.user r WHERE r.id = :id")
    public  Advertisement findAdevertisementByUser(@Param("id") Long id);

	@Query("Select m.advertisement FROM Contrat m WHERE m.id = :id")
	public  List<Contrat> findContratByUsername(@Param("id") Long id);

	@Query("Select m.advertisement FROM Contrat m WHERE m.id = :id")
	  public  Advertisement findAdvertisementByContrat(@Param("id") Long id);
	@Query("Select m.price FROM Advertisement m WHERE m.id = :id")
	  public  float findPriceByAdvertisement(@Param("id") Long id);

	@Query("Select m.contrat FROM Purchase m WHERE m.id = :id")
	  public Contrat findContratByPurchase(@Param("id") Long id);
       
     public List<String> findTitres();
     public Contrat getContratById(Long id);
     
     public void updateContratDePurchase(Contrat u);
     
     
	  public List<Contrat> recupéreContratParUtilisateur(@Param("UserName") String UserName);
	  public List<Contrat> recupéreContratParUtilisateurS(@Param("UserName") String UserName,@Param("status") String status);
	  public Contrat recupéreContrat(@Param("id") Long id);
	  //filtre de recherche
	  public List<Contrat> Search(String word);
	  public float calculP();
	  public int searchN();
	  public Contrat searchContrat();
	  public float price();
	  
	//filtre de recherche par area
	  public List<Contrat> SearchParArea(String word);
}
