package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Loan;
import tn.esprit.spring.repository.BankRepository;
import tn.esprit.spring.repository.LoanRepository;

@Service
public class LoanServiceImp implements ILoanService {

	@Autowired	
	LoanRepository loanRepository;
	BankRepository bankRepository;
	@Override
	public List<Loan> retrieveAllLoan() {
		// TODO Auto-generated method stub
		return (List<Loan>) loanRepository.findAll();
	}

	@Override
	public Loan addLoan(Loan u) {
		// TODO Auto-generated method stub
		return loanRepository.save(u);
	}

	@Override
	public void deleteLoan(int id) {
		// TODO Auto-generated method stub
		loanRepository.deleteById((long) id);
	}

	@Override
	public Loan retrieveLoan(@PathVariable String id) {
		// TODO Auto-generated method stub
		return loanRepository.findById(Long.parseLong(id)).orElse(null); 
	}

	@Override
	public void affecterBankLouer(String idLoan, String idbank) {
		// TODO Auto-generated method stub
		Loan loan=loanRepository.findById(Long.parseLong(idLoan)).orElse(null); 
		Bank bank=bankRepository.findById(Long.parseLong(idbank)).orElse(null);
		loan.setBank(bank);	
	}

	@Override
	public float calculLoan(Long id) {
		// TODO Auto-generated method stub
		Loan s=loanRepository.findById(id).orElse(null);
		List<String> List= new ArrayList<String>();
		
			List.add(String.valueOf(s.getLoanAmount()+(s.getInterestRate()*s.getLoanAmount())));
		return Float.parseFloat(List.get(0)); 
	}

	@Autowired	
	LoanServiceImp Loansimp;
	@Override
	public float calculLoanMonthly(Long id) {
		// TODO Auto-generated method stub
		Loan s=loanRepository.findById(id).orElse(null);
		float r=Loansimp.calculLoan(id);
		List<String> List= new ArrayList<String>();
		List.add(String.valueOf(r/(s.getLoanTerm()*12)));
		return Float.parseFloat(List.get(0)); 
	}

	@Override
	public float calculLoanMonthlyMax(Long id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Loan s=loanRepository.findById(id).orElse(null);
				List<String> List= new ArrayList<String>();
				List.add(String.valueOf(s.getMonthlyIncome()*0.4));
				return Float.parseFloat(List.get(0)); 
	}

	@Override
	public List<Loan> SearchL(String word) {
		// TODO Auto-generated method stub
		return loanRepository.searchLoan(word);
	}

	@Override
	public void updateLoan(Loan u) {
		// TODO Auto-generated method stub
		loanRepository.updateL(u.getLoanAmount(), u.getLoanTerm(),u.getMonthlyIncome(),u.getBank(), u.getId());
	}
	

}
