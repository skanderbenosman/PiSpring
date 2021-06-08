package tn.esprit.spring.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Purchase;
import tn.esprit.spring.service.IContratService;
import tn.esprit.spring.service.IPurchaseService;
import tn.esprit.spring.control.SimpleEmailExampleController;

@RestController
@RequestMapping("/Purchase")
public class PurchaseRestControlImp {

	@Autowired
	IPurchaseService purchaseService;
	IContratService us;
	PurchaseRestControlImp t;
	@Autowired
	SimpleEmailExampleController tmail;
	@RequestMapping(value = "/AddPurchase", method = RequestMethod.POST)
	@ResponseBody
	public void add1Purchase(@RequestBody Purchase u) {
      
 /*  if(u.getPrix()!=u.getContrat().getAdvertisement().getPrice()){
		System.out.println("vérifier le prix de produit :");
	}else{*/
		purchaseService.addPurchase(u);
		
/*		System.out.println("ajout avec succées :");
	}   */
	
	}

	
	// http://localhost:8081/SpringMVC/servlet/Purchase/AllPurchases
	@RequestMapping(value = "/AllPurchases", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ArrayList<Purchase> getPurchases() {
		ArrayList<Purchase> list = (ArrayList<Purchase>) purchaseService.retrieveAllPurchase();
	return list;}
	
	//Supprimer User : http://localhost:8081/SpringMVC/servlet/Purchase/deletePurchase/{user-id}
	@DeleteMapping("/deletePurchase/{user-id}")
	@ResponseBody
	public void deletePurchase(@PathVariable("user-id") int userId) {
		purchaseService.deletePurchase(userId);
	
}
	@RequestMapping(value = "/FindContratParPurchase/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Contrat FindContratParPurchase(@PathVariable Long id) {
		//ArrayList<Purchase> list = (ArrayList<Purchase>) purchaseService.retrieveAllPurchase();
	return purchaseService.findContratByPurchase(id);}
	
	
	@RequestMapping(value = "/rechercheP/{word}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Purchase> rechercheP(@PathVariable String word) {
	 return  purchaseService.SearchP(word);
	}
	
	
	@RequestMapping(value = "/PurchasesParUser/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Purchase> PurchasesParUser(@PathVariable Long id) {
	 return  purchaseService.recupérePurchaseByUser(id);
	}
	
}




