package dari.tn.controller;
import java.util.List;

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
	
	@GetMapping("/getall")  
	private List<Subscription> getAllSubs()   
	{  
		 System.out.println("done");
	return abonnementService.getAllSubscriptions();  

	}  
	
	@GetMapping("/get/{abonnement_id}")  
	private Subscription getSubscritpion(@PathVariable("abonnement_id") int abonnement_id)   
	{  
	return abonnementService.getSubscriptionById(abonnement_id);  
	}  
	
	@DeleteMapping("/delete/{abonnement_id}")  
	private void deleteSubscritpion(@PathVariable("abonnement_id") int abonnement_id)   
	{  
	abonnementService.delete(abonnement_id);  
	}  
	
	@PostMapping("/subscribe")
	@ResponseBody
	private int saveSubscritpion(@RequestBody Subscription abonnement)   
	{  
	abonnementService.saveOrUpdate(abonnement);  
	return abonnement.getSubscription_id();  
	}  
	
	@PutMapping("/update")  
	private Subscription update(@RequestBody Subscription abonnement)   
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
	

    