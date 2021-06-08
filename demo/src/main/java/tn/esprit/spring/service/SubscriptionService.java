package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



import tn.esprit.spring.entity.Subscribe;
import tn.esprit.spring.entity.Subscription;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.*;


@Service  
public class SubscriptionService {
	@Autowired  
	SubscriptionRepository abonnementRepository;  
	@Autowired
	UserRepository userRepository;
	@Autowired
    SubscribeRepository subrep;
    
    public EntityManager entityManager;
    
    
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
			subscr.setSubscription_duration(sub.getSubscription_duration());
	}
	
	
}

public String AddSubTo(int idS, Long long1,Date dateD,Date dateF) {

    Subscribe s = new Subscribe();
    s.setDateD(dateD);
    s.setDateF(dateF);
    Optional<Subscription> su = abonnementRepository.findById(idS);
    Optional<User> u = userRepository.findById(long1);
    if (u.isPresent() && su.isPresent()){
	Subscription su1=su.get();
	User u1=u.get();
   s.setUser(u1);
   u1.setSolde(u1.getSolde() - su1.getSubscription_price());
   s.setSubscription(su1);
 s.setPaid(true);
   subrep.save(s);
   userRepository.save(u1);
    System.out.println("test"+su1.getSubscription_id()+u1.getId());

    }
    
    
 //   
   return "idS";
   
}


public Subscribe EndAbo(int idS) {
    Subscribe subsc = subrep.findById(idS).orElse(null);
    if(subsc.getDateF().compareTo(Calendar.getInstance().getTime())<0){
        subsc.setPaid(false);
    }
    return subsc ;
}

@Transactional
public void insertWithQuery(Subscribe sub) {
    entityManager.createNativeQuery("INSERT INTO Subscribe (dated,datef,paid,utilisateur_id,id_sub) VALUES (?,?,?,?)")
            .setParameter(1, sub.getDateD())
            .setParameter(2, sub.getDateF())
            .setParameter(3, sub.isPaid())
            .setParameter(4, sub.getUser().getId())
            .setParameter(5,sub.getSubscription().getSubscription_id())
            .executeUpdate();
}

@Value("${STRIPE_SECRET_KEY}")
private String API_SECRET_KEY;

@Autowired
public void SubscriptionRepository() {
   // Stripe.apiKey = API_SECRET_KEY;
}

//public Charge chargeNewCard(String token, double amount) throws Exception {
  //  Map<String, Object> chargeParams = new HashMap<>();
    //chargeParams.put("amount", (int)(amount * 100));
   // chargeParams.put("currency", "USD");
    //chargeParams.put("source", token);
    //Charge charge = Charge.create(chargeParams);
    //return charge;
//}


public List<Subscription> Search(String word) {
	return (List<Subscription>) abonnementRepository.searchEvent(word);
}

@GetMapping("/test/{idt}")
public User test (@PathVariable Long idt){
	User u1 = null;
	Optional<User> u = userRepository.findById(idt);
    if (u.isPresent()){
	u1=u.get();
//	Subscription su1=su.get();
 //   s.setUser(u1);
//    s.setSubscription(su1);
//    s.setPaid(true);
 //   subrep.save(s);
    System.out.println("test"+u1.getId());
    return u1;

    }
    
    return u1;
}
}
