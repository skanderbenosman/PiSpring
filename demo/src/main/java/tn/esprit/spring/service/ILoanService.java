package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Loan;
import tn.esprit.spring.entity.Purchase;

public interface ILoanService {
	List<Loan> retrieveAllLoan(); 

	Loan addLoan(Loan u);

	void deleteLoan(int i);

//	void updateLoan(Loan u);

	Loan retrieveLoan(String id);
	 void affecterBankLouer(String idLoan, String idbank);
	 //Les fonctions de calculs
	 float calculLoan(Long id);
	 float calculLoanMonthly(Long id);
	 float calculLoanMonthlyMax(Long id);
	 
	 public List<Loan> SearchL(String word);
	 void updateLoan(Loan u);
}
