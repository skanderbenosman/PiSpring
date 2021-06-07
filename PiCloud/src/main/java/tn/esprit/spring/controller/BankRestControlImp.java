package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Loan;
import tn.esprit.spring.entity.Purchase;
import tn.esprit.spring.service.IBankService;
import tn.esprit.spring.service.IPurchaseService;

@RestController
@RequestMapping("/Bank")
public class BankRestControlImp {
	@Autowired
	IBankService bankService;
	@RequestMapping(value = "/AddBank", method = RequestMethod.POST)
	@ResponseBody
	public void addPurchase(@RequestBody Bank u) {
      
		bankService.addBank(u);

	}
	// http://localhost:8081/SpringMVC/servlet/Purchase/AllPurchases
	@RequestMapping(value = "/AllBanks", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ArrayList<Bank> getPurchases() {
		ArrayList<Bank> list = (ArrayList<Bank>) bankService.retrieveAllBank();
	return list;}
	
	//Supprimer User : http://localhost:8081/SpringMVC/servlet/Purchase/deletePurchase/{user-id}
	@DeleteMapping("/deleteBank/{user-id}")
	@ResponseBody
	public void deletePurchase(@PathVariable("user-id") int userId) {
		bankService.deleteBank(userId);;
	
}
	@PutMapping("/UpdateBank")
	@ResponseBody
	public void updateContrat(@RequestBody Bank u) {
		bankService.updateBank(u);
	}
	
	@RequestMapping(value = "/recherche/{word}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Bank> recherche(@PathVariable String word) {
	 return bankService.Search(word);
	}
}
