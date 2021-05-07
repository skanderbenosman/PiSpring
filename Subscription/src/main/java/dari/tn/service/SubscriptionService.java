package dari.tn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dari.tn.model.Subscription;
import dari.tn.repository.SubscriptionRepository;

@Service  
public class SubscriptionService {
	@Autowired  
	SubscriptionRepository abonnementRepository;  
	public List<Subscription> getAllSubscriptions()   
	{  
	List<Subscription> abonnements = new ArrayList<Subscription>();  
	abonnementRepository.findAll().forEach(abonnement1 -> abonnements.add(abonnement1));  
	return abonnements;  
	}  
	
	public Subscription getSubscriptionById(int id)   
	{  
	return abonnementRepository.findById(id).get();  
	}  
	

	
	public void saveOrUpdate(Subscription abonnements)   
	{  
	abonnementRepository.save(abonnements);  
	}  
	
	public void delete(int id)   
	{  
	abonnementRepository.deleteById(id);  
	}  
	
	public void update(Subscription abonnement, int abonnement_id)   
	{  
	abonnementRepository.save(abonnement);  
	}  
	
    public Subscription Add(Subscription S) {

        return abonnementRepository.save(S);
    }

}
