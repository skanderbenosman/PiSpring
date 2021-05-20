package dari.tn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.stripe.Stripe;
import com.stripe.model.Charge;

import dari.tn.model.Subscription;
import dari.tn.model.Utilisateur;
import dari.tn.repository.SubscribeRepository;
import dari.tn.repository.SubscriptionRepository;
import dari.tn.repository.UtilisateurRepository;
import dari.tn.model.Subscribe;

@Service  
public class SubscriptionService {
	@Autowired  
	SubscriptionRepository abonnementRepository;  
    private EntityManager entityManager;
    UtilisateurRepository utilisateurRepository;
    SubscribeRepository subrep;
    
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

public void updateSub(@PathVariable int id, @RequestBody Subscription sub) {
		
		Optional<Subscription> subs = abonnementRepository.findById(id);
		if (subs.isPresent()){
			Subscription subscr = subs.get();
			subscr.setSubscription_title(sub.getSubscription_title());
			subscr.setSubscription_offer(sub.getSubscription_offer());
			subscr.setSubscription_price(sub.getSubscription_price());

	}
	
	
}

public Subscribe AddSubTo(int idS, Long long1, Date dateD, Date dateF) {

    Subscribe s = new Subscribe();
    s.setDateD(new Date());
    s.setDateF(new Date());
	Utilisateur u = utilisateurRepository.findById(long1).get();

    s.setUtilisateur(u);
    Subscription su = abonnementRepository.findById(idS).get();
    s.setSubscription(su);
    s.setPaid(true);
    return subrep.save(s);
}


public Subscribe EndAbo(int idS) {
    Subscribe subsc = subrep.findById(idS).orElse(null);
    if(subsc.getDateF().compareTo(new Date())<0){
        subsc.setPaid(false);
    }
    return subsc ;
}

@Transactional
public void insertWithQuery(Subscribe sub) {
    entityManager.createNativeQuery("INSERT INTO Subscribe (dated,datef,utilisateur_id,id_sub) VALUES (?,?,?,?)")
            .setParameter(1, sub.getDateD())
            .setParameter(2, sub.getDateF())
            .setParameter(3, sub.getUtilisateur().getUtilisateurId())
            .setParameter(4,sub.getSubscription().getSubscription_id())
            .executeUpdate();
}

@Value("${STRIPE_SECRET_KEY}")
private String API_SECRET_KEY;

@Autowired
public void SubscriptionRepository() {
    Stripe.apiKey = API_SECRET_KEY;
}

public Charge chargeNewCard(String token, double amount) throws Exception {
    Map<String, Object> chargeParams = new HashMap<>();
    chargeParams.put("amount", (int)(amount * 100));
    chargeParams.put("currency", "USD");
    chargeParams.put("source", token);
    Charge charge = Charge.create(chargeParams);
    return charge;
}


public List<Subscription> Search(String word) {
	return (List<Subscription>) abonnementRepository.searchEvent(word);
}
}
