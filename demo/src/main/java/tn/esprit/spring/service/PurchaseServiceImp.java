package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.control.UserControllerImpl;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Purchase;
import tn.esprit.spring.repository.PurchaseRepository;
@Service
public class PurchaseServiceImp implements IPurchaseService {

	
	@Autowired	
	PurchaseRepository purchaseRepository;
	IContratService us;
	IPurchaseService us1;
	@Override
	public List<Purchase> retrieveAllPurchase() {
		// TODO Auto-generated method stub
		
		return  (List<Purchase>) purchaseRepository.findAll();
	}

	@Override
	public String addPurchase(Purchase u) {
		// TODO Auto-generated method stub
	/*	System.out.println("Test2"+u.getContrat().getId());
		 purchaseRepository.save(u);
		 Contrat s1=us1.findContratByPurchase(Long.valueOf(u.getId()));
		 if(us.findPriceByAdvertisement(Long.valueOf(s1.getId())) != u.getPrix())
		 { purchaseRepository.deleteById((long) u.getId());
		return "ajout non succées choisisr bonne prix"; }
		 else{*/
			 purchaseRepository.save(u);
		return "ajout avec sucéées";
		// }
		 
	}

	@Override
	public void deletePurchase(int id) {
		// TODO Auto-generated method stub
		purchaseRepository.deleteById((long) id);
	}

	@Override
	public Purchase updateContrat(Purchase u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Purchase retrievePurchase(String id) {
		// TODO Auto-generated method stub
		return purchaseRepository.findById(Long.parseLong(id)).orElse(null);
	}

	@Override
	public Contrat findContratByPurchase(Long id) {
		// TODO Auto-generated method stub
		return purchaseRepository.findContratByPurchase(id);
	}

	@Override
	public Contrat updateContratByPurchase(Long id) {
		// TODO Auto-generated method stub
		return purchaseRepository.UpdateContrat(id);
	}

	@Override
	public List<Purchase> SearchP(String word) {
		// TODO Auto-generated method stub
		return purchaseRepository.searchPurchase(word);
	}

	@Override
	public List<Purchase> recupérePurchaseByUser(Long id) {
		// TODO Auto-generated method stub
		return purchaseRepository.findPurchaseByUser(id);
	}

	

	

	

	

}
