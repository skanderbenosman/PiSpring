package tn.esprit.spring.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Advertisement.Type;
import tn.esprit.spring.service.IContratService;

@RestController
@RequestMapping("/Contrat")
public class ContratRestControlImp {
	@Autowired
	IContratService contratService;
	// http://localhost:8081/SpringMVC/servlet/
/*	@RequestMapping("/")
	@ResponseBody
	public String welcome() { return "Bonjour, Bienvenue à l'application de test des Web Services REST."; }

*/
	// URL : http://localhost:8081/SpringMVC/servlet/retrieve-all-users
	@RequestMapping(value = "/AllContrats", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ArrayList<Contrat> getContrats() {
		ArrayList<Contrat> list = (ArrayList<Contrat>) contratService.retrieveAllContrat();
	  for(Contrat a:list){
		  Date date = new Date(System.currentTimeMillis());
		  if(a.getDateS().before((date)) && a.getStatus()=="en attente")
			 deleteEmployee(a.getId().intValue());
		 
	  }
		return list;}

	// http://localhost:8081/SpringMVC/servlet/ retrieve-user /{user-id}
	@RequestMapping(value = "/FindContrat/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Contrat getcontrat(@PathVariable String userId) {
	return contratService.retrieveContrat(userId);
	}
	//Ajouter User : http://localhost:8081/SpringMVC/servlet/add-user
	@RequestMapping(value = "/AddContrat", method = RequestMethod.POST)
	@ResponseBody
	public void addcontrat(@RequestBody Contrat u) {
		
	contratService.addContrat(u);  

	}
	//Supprimer User : http://localhost:8081/SpringMVC/servlet/ delete-user/{user-id}
	@DeleteMapping("/deleteContrat/{user-id}")
	@ResponseBody
	public void deleteEmployee(@PathVariable("user-id") int userId) {
		contratService.deleteContrat(userId);
	}

	@PutMapping("/UpdateContrats")
	@ResponseBody
	public void updateContrat(@RequestBody Contrat u) {
	 contratService.updateContrat(u);
	}
	
	
	@RequestMapping(value = "/FindContratByUser/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ArrayList<Contrat>  update1Contrat(@PathVariable Long id) {
		List<Contrat> List= new ArrayList<Contrat>();
		Iterable<Contrat> ads =contratService.findContratByUsername(id);
		
		for(Contrat a: ads){
			if(a.getAdvertisement().getType()==Type.Sell){
			List.add(a);
			System.out.println(a.toString());}
		}
	 return (ArrayList<Contrat>) List;
	}
	@RequestMapping(value = "/FindADVByContrat/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Advertisement  findAdvByContrat(@PathVariable Long id) {
	 return  contratService.findAdvertisementByContrat(id);
	}
	@RequestMapping(value = "/FindPriceByADV/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public float FindPriceByADV(@PathVariable Long id) {
	 return  contratService.findPriceByAdvertisement(id);
	}
	
	@RequestMapping(value = "/FindContratParPurchase/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Contrat FindContratParPurchase(@PathVariable Long id) {
		//ArrayList<Purchase> list = (ArrayList<Purchase>) purchaseService.retrieveAllPurchase();
	return contratService.findContratByPurchase(id);}
	
	
	@PutMapping("/UpdateContrat")
	@ResponseBody
	public void UpdateContrat1(@RequestBody Contrat u) {
		//ArrayList<Purchase> list = (ArrayList<Purchase>) purchaseService.retrieveAllPurchase();
	 contratService.updateContratDePurchase(u); }
	
	@RequestMapping(value = "/FindContrats/{UserName}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Contrat> FindContrats(@PathVariable String UserName) {
	 return  contratService.recupéreContratParUtilisateur(UserName);
	}
	@RequestMapping(value = "/FindContratsS/{UserName}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Contrat> FindContratsS(@PathVariable String UserName) {
	 return  contratService.recupéreContratParUtilisateurS(UserName, "en attente");
	}
	
	// recupérer contrat par RDV
	@RequestMapping(value = "/FindContratADV/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Contrat FindContratADV(@PathVariable Long id) {
	 return  contratService.recupéreContrat(id);
	}
	
	@RequestMapping(value = "/recherche/{word}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Contrat> recherche(@PathVariable String word) {
		List<Contrat> List= new ArrayList<Contrat>();
		List<Contrat> List1= contratService.SearchParArea(word);
		for(Contrat a:List1){
			if(a.getAdvertisement().getArea()==Float.parseFloat(word)){
				List.add(a);
			}
		}
	 return List;
	}
	
	@RequestMapping(value = "/recherche1", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public int CountContrat() {
	 return  contratService.searchN();
	}
	
	@RequestMapping(value = "/searchCAttente", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Contrat searchContrat() {
	 return  contratService.searchContrat();
	}
	
	
	@RequestMapping(value = "/calculPrix", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public float calculPrixADV() {
	 return  contratService.calculP();
	}
	
	
	@RequestMapping(value = "/Prix", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public float PrixADV() {
		if(contratService.searchContrat()==null)
		{ return 0; }
		return contratService.searchContrat().getAdvertisement().getPrice();
		}
	@RequestMapping(value = "/rechercheParArea/{word}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Contrat> rechercheParArea(@PathVariable String word) {
		List<Contrat> List= new ArrayList<Contrat>();
		List<Contrat> List1= contratService.SearchParArea(word);
		for(Contrat a:List1){
			if(a.getAdvertisement().getArea()==Float.parseFloat(word)){
				List.add(a);
			}
		}
	 return List;
	}
	
	@RequestMapping(value = "/Numb/{dateS}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public float calculNumbreCont(@PathVariable Date dateS) {
	 return  contratService.searchStatisticContrat(dateS);
	}
}
