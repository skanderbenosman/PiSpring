package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.control.ContratRestControlImp;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImp implements IContratService {

	@Autowired	
	ContratRepository ContRepository;
	@Override
	public List<Contrat> retrieveAllContrat() {
		// TODO Auto-generated method stub
		return (List<Contrat>) ContRepository.findAll();
	}

	@Override
	public Contrat addContrat(@RequestBody Contrat u) {
		// TODO Auto-generated method stub
	/*	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       //obtenir la date courante
	       Date date = new Date();
	       System.out.println("date contrat"+format.format(u.getDateS()));
	       System.out.println("date aujourd'hui"+format.format(date));
	      if(format.format(u.getDateS()) ==format.format(date)){  */
		return ContRepository.save(u);
	   /*   }
		return u; */
	}
	
/*	@RequestMapping(value = "/findContratParUser/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Advertisement  findAllMenuByIdRole(@PathVariable Long id)
	{
		return ContRepository.findByIdRole(id);
	} */

	@Override
	public void deleteContrat(int id) {
		// TODO Auto-generated method stub
		ContRepository.deleteById((long) id);
	}

	@Override
	public void updateContrat(@RequestBody Contrat u) {
	
		ContRepository.majname(u.getStatus(),u.getTitre(), u.getDescription(), u.getDateS(), u.getId());
	}

	@Override
	public Contrat retrieveContrat(@PathVariable String id) {
		// TODO Auto-generated method stub
		return ContRepository.findById(Long.parseLong(id)).orElse(null); 
	}
/*
	@Override
	public Advertisement findByIdRole(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return ContRepository.findByIdRole1(id);
	}  */

	@Override
	public List<Contrat>  findContratByUsername(Long UserName) {
		// TODO Auto-generated method stub
		return (List<Contrat>) ContRepository.findContratByUsername(UserName);
	}

@Override
public Advertisement findAdevertisementByUser(@PathVariable Long id) {
	// TODO Auto-generated method stub
	return ContRepository.findAdevertisement1ByUser(id);
}

@Override
public Advertisement findAdvertisementByContrat(Long id) {
	// TODO Auto-generated method stub
	return ContRepository.findAdvertisementByContrat(id);
}

@Override
public float findPriceByAdvertisement(Long id) {
	// TODO Auto-generated method stub
	return ContRepository.findPriceByAdvertisement(id);
}

@Override
public Contrat findContratByPurchase(Long id) {
	// TODO Auto-generated method stub
	return ContRepository.findContratByPurchase(id);
}

@Override
public List<String> findTitres() {
	List<String> List= new ArrayList<String>();
	Iterable<Contrat> con =ContRepository.findAll();
	 System.out.println("skander");
	for(Contrat a: con){
		
		List.add(a.getTitre());
		System.out.println(a.toString());
	}
	return List;
}

@Override
public Contrat getContratById(Long id){
	System.err.println("hava id fi contrat service :"+id);
	return ContRepository.findById(id).get();
}

@Override
public void updateContratDePurchase(@RequestBody Contrat u) {
	// TODO Auto-generated method stub
	ContRepository.updateContrat("valid",u.getId());
	
}


@Override
public List<Contrat> recupéreContratParUtilisateur(String UserName) {
	// TODO Auto-generated method stub
	return ContRepository.findContratParUtilisateur(UserName);
}

@Override
public List<Contrat> recupéreContratParUtilisateurS(String UserName, String status) {
	// TODO Auto-generated method stub
	return ContRepository.findContratParUtilisateurS(UserName, status);
}

@Override
public Contrat recupéreContrat(Long id) {
	// TODO Auto-generated method stub
	return ContRepository.findContratByAdv(id);
}

@Override
public List<Contrat> Search(String word) {
	// TODO Auto-generated method stub
	return ContRepository.searchContrat(word);
}

@Autowired
ContratRestControlImp conRest;
@Override
public float calculP() {
	
	Contrat t=conRest.searchContrat();
	List<String> List= new ArrayList<String>();
	float y = 0;
	// TODO Auto-generated method stub
//	ContRepository.findAdvertisementByContrat(id);
	if(t.getAdvertisement().getAddress()=="sousse")
		    y+=2820*t.getAdvertisement().getArea();
	        List.add(String.valueOf(2820*t.getAdvertisement().getArea()));
	
	
	        if(t.getAdvertisement().getAddress()=="mounastir")
	          y+= 2600*t.getAdvertisement().getArea();
	         List.add(String.valueOf(2600*t.getAdvertisement().getArea()));
	        
	
	if(t.getAdvertisement().getAddress()=="ben arous")
	   y+= 1500*t.getAdvertisement().getArea();
	   List.add(String.valueOf(1500*t.getAdvertisement().getArea()));
	
	return Float.parseFloat(List.get(0));
		
	
}

@Override
public int searchN() {
	// TODO Auto-generated method stub
	return ContRepository.search1Contrat();
}

@Override
public Contrat searchContrat() {
	// TODO Auto-generated method stub
	return ContRepository.ContratAttente();
}

@Override
public float price() {
	// TODO Auto-generated method stub
	return ContRepository.PrixADV();
}

@Override
public List<Contrat> SearchParArea(String word) {
	// TODO Auto-generated method stub
	return ContRepository.searchContratParArea(word);
}

@Override
public int searchStatisticContrat(Date dateS) {
	// TODO Auto-generated method stub
	return ContRepository.searchStatisticContrat(dateS);
}
     
}
