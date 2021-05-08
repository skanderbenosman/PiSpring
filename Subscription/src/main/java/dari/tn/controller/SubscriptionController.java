package dari.tn.controller;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dari.tn.model.Subscription;
import dari.tn.service.SubscriptionService;

@RestController
@Scope(value = "session")
@Controller(value = "subscriptionController")
@ELBeanName(value = "subscriptionController")
@Join(path = "/", to = "/login.jsf")public class SubscriptionController{
	@Autowired  
	SubscriptionService abonnementService;
	public int subscription_id;
	public String subscription_offer;
	public String subscription_title;
	public int subscription_price;
	
	public List<Subscription> ListSubs=new ArrayList<Subscription>();
	
	
	public List<Subscription> getAllSubs()   
	{  
		 System.out.println("done");
	ListSubs= abonnementService.getAllSubscriptions();  
	return ListSubs;  

	}  
	
	public int getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}

	public String getSubscription_offer() {
		return subscription_offer;
	}

	public void setSubscription_offer(String subscription_offer) {
		this.subscription_offer = subscription_offer;
	}

	public String getSubscription_title() {
		return subscription_title;
	}

	public void setSubscription_title(String subscription_title) {
		this.subscription_title = subscription_title;
	}

	public int getSubscription_price() {
		return subscription_price;
	}

	public void setSubscription_price(int subscription_price) {
		this.subscription_price = subscription_price;
	}

	public List<Subscription> getListSubs() {
		return ListSubs;
	}

	public void setListSubs(List<Subscription> listSubs) {
		ListSubs = listSubs;
	}

	@GetMapping("/get/{abonnement_id}")  
	public Subscription getSubscritpion(@PathVariable("abonnement_id") int abonnement_id)   
	{  
	return abonnementService.getSubscriptionById(abonnement_id);  
	}  
	
	@DeleteMapping("/delete/{abonnement_id}")  
	public void deleteSubscritpion(@PathVariable("abonnement_id") int abonnement_id)   
	{  
	abonnementService.delete(abonnement_id);  
	}  
	
	@PostMapping("/subscribe")
	@ResponseBody
	public String saveSubscription(@RequestBody Subscription abonnement)   
	{  
	abonnementService.saveOrUpdate(abonnement);
	return "/Subs.xhtml?faces-redirect=true";
	}  
	
	@PutMapping("/update")  
	public Subscription update(@RequestBody Subscription abonnement)   
	{  
	abonnementService.saveOrUpdate(abonnement);  
	return abonnement;  
	}  
	
    @PostMapping("/abonner")
    @ResponseBody
    public ResponseEntity<?> Add(@RequestBody Subscription s){
        Subscription sub = abonnementService.Add(s);
        return new ResponseEntity<>( " Subscription added. ",  HttpStatus.OK);
    }
    
    }
	

    