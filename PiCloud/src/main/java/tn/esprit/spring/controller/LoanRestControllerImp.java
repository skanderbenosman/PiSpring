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
import tn.esprit.spring.service.ILoanService;

@RestController
@RequestMapping("/Loan")
public class LoanRestControllerImp {
	@Autowired
	ILoanService loanService;
	
	@RequestMapping(value = "/AllLoans", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ArrayList<Loan> getLoans() {
		ArrayList<Loan> list = (ArrayList<Loan>) loanService.retrieveAllLoan();
	return list;}

	// http://localhost:8081/SpringMVC/servlet/ retrieve-user /{user-id}
	@RequestMapping(value = "/FindLoan/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Loan getcontrat(@PathVariable String userId) {
	return loanService.retrieveLoan(userId);
	}
	//Ajouter User : http://localhost:8081/SpringMVC/servlet/add-user
	@RequestMapping(value = "/AddLoan", method = RequestMethod.POST)
	@ResponseBody
	public void addloan(@RequestBody Loan u) {
		
		loanService.addLoan(u);  

	}
	
	@RequestMapping(value = "/CalculLoan/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public float calculloan(@PathVariable Long userId) {
		
		return loanService.calculLoan(userId); 
//		}
	}
	
	@RequestMapping(value = "/CalculLoanMonthly/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public float calculloanMonthly(@PathVariable Long userId) {
		
		return loanService.calculLoanMonthly(userId); 
//		}
	}
	
	
	@RequestMapping(value = "/calculloanMonthlyMax/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public float calculloanMonthlyMax(@PathVariable Long userId) {
		
		return loanService.calculLoanMonthlyMax(userId);
//		}
	}
	//Supprimer User : http://localhost:8081/SpringMVC/servlet/ delete-user/{user-id}
	@DeleteMapping("/deleteLoan/{user-id}")
	@ResponseBody
	public void deleteLoan(@PathVariable("user-id") int userId) {
		loanService.deleteLoan(userId);
	}

/*	@PutMapping("/UpdateContrats")
	@ResponseBody
	public void updateContrat(@RequestBody Contrat u) {
	 contratService.updateContrat(u);
	} */
	
	@RequestMapping(value = "/recherche/{word}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Loan> recherche(@PathVariable String word) {
	 return loanService.SearchL(word);
	}
	
	@PutMapping("/UpdateLoan")
	@ResponseBody
	public void updateLoan(@RequestBody Loan u) {
		loanService.updateLoan(u);
	}
	
}
