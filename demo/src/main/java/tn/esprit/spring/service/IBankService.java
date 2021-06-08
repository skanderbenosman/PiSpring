package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Contrat;

public interface IBankService {
	List<Bank> retrieveAllBank(); 

	Bank addBank(Bank u);

	void deleteBank(int i);

	void updateBank(Bank u);

	Bank retrieveBank(String id);
	Bank getBankById(Long id);
	public List<Bank> Search(String word);
}
