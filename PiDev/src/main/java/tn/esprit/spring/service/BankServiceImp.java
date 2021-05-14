package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.repository.BankRepository;

@Service
public class BankServiceImp implements IBankService {

	@Autowired	
	BankRepository bankRepository;
	@Override
	public List<Bank> retrieveAllBank() {
		// TODO Auto-generated method stub
		return (List<Bank>) bankRepository.findAll();
	}

	@Override
	public Bank addBank(Bank u) {
		// TODO Auto-generated method stub
		return bankRepository.save(u);
	}

	@Override
	public void deleteBank(int id) {
		// TODO Auto-generated method stub
		bankRepository.deleteById((long) id);
	}

	@Override
	public void updateBank(Bank u) {
		// TODO Auto-generated method stub
		bankRepository.updateB(u.getNameBank(), u.getId());
	}

	@Override
	public Bank retrieveBank(@PathVariable String id) {
		// TODO Auto-generated method stub
		return bankRepository.findById(Long.parseLong(id)).orElse(null);
	}
	
	public Bank getIdEvent(String NameBank) {
		// TODO Auto-generated method stub
		return bankRepository.getIDbank(NameBank);
	}
	@Override
	public Bank getBankById(Long id){
		System.err.println("hava id fi bank service :"+id);
		return bankRepository.findById(id).get();
	}
	

}
