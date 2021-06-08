package tn.esprit.spring.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.control.ContratRestControlImp;
import tn.esprit.spring.control.SimpleEmailExampleController;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.service.AdvertisementServiceImp;
import tn.esprit.spring.service.BankServiceImp;
import tn.esprit.spring.service.ContratServiceImp;
import tn.esprit.spring.service.IContratService;

@Scope(value = "session")
@Controller(value = "contratController")
@ELBeanName(value = "contratController")
@Join(path = "/", to = "/login.jsf")
public class ContratControllerImp {
	@Autowired
	IContratService icontratService;
	private Long id;
    private String titre;
    private String description;
    private Date dateS;
    private Advertisement advertisement;
    private String UserName;
    private long selectedadsid;
    private List <Contrat> Contrats;
    private List <Contrat> Cc;
    private List<String> titres;
    private String word;
	public ContratControllerImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public IContratService getIcontratService() {
		return icontratService;
	}
	public void setIcontratService(IContratService icontratService) {
		this.icontratService = icontratService;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateS() {
		return dateS;
	}
	public void setDateS(Date dateS) {
		this.dateS = dateS;
	}



	public List<Contrat> getContrats() {
		 System.err.println("word ici*** "+this.word);
		if (word== null){
		Contrats=icontratService.recupéreContratParUtilisateur(UserControllerImpl.getT().getFirstName());
		} else
		{   
			Contrats=icontratService.Search(word);          } 
		return Contrats;
	}

	public void removeContrat(int employeid)
	{
		icontratService.deleteContrat(employeid);
	}

	public void setContrats(List<Contrat> contrats) {
		Contrats = contrats;
	}



	public Advertisement getAdvertisement() {
		return advertisement;
	}



	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}



	public String getUserName() {
		return UserName;
	}



	public void setUserName(String userName) {
		UserName = userName;
	}
	
	@Autowired
	AdvertisementServiceImp bs;
	@Autowired
	SimpleEmailExampleController tmail;
	@Autowired
	ContratRestControlImp tmail1;
	public void ajouterContrat()
	{
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date(System.currentTimeMillis());
		 System.err.print("nombre de requette en attente **"+tmail1.CountContrat());
		 String d1 = new Date(System.currentTimeMillis()).toString();  
		 
		  //Spécifier le format de date correspondant à la date d1
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar cal = Calendar.getInstance();
		  Calendar cal3 = Calendar.getInstance();
		     
		  //Nombre de jours à ajouter
		  cal3.add(Calendar.DAY_OF_MONTH, 0); 
		  cal.add(Calendar.DAY_OF_MONTH, 3);  
		  //Date après avoir ajouté les jours à la date indiquée
		  String d2 = sdf.format(cal.getTime());
		  String d3 = sdf.format(cal3.getTime()); 
		  System.out.println("Date avant l'addition: "+d3);
		  System.out.println("Date après l'addition: "+d2);
		   this.advertisement=bs.getAdvById(this.selectedadsid);
		 System.err.println("*********"+this.advertisement.getAddress());
		if(tmail1.CountContrat()>=1){
			FacesMessage facesMessage =

					new FacesMessage("Ajout Failed: poursuivre les étapes d'achat ");
			FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		else{
		 icontratService.addContrat(new Contrat(id, titre, description, cal3.getTime(), advertisement, UserControllerImpl.getT().getFirstName()));
		
		}
	}


	@Autowired
	ContratServiceImp conimp;
	public List<String> getTitres() {
		titres=icontratService.findTitres();
		return titres;
	}



	public void setTitres(List<String> titres) {
		this.titres = titres;
	}



	public long getSelectedadsid() {
		return selectedadsid;
	}



	public void setSelectedadsid(long selectedadsid) {
		this.selectedadsid = selectedadsid;
	}



	public List<Contrat> getCc() {
		if(icontratService.recupéreContratParUtilisateurS(UserControllerImpl.getT().getFirstName(),"en attente")==null)
		{return null;}
		else{Cc=icontratService.recupéreContratParUtilisateurS(UserControllerImpl.getT().getFirstName(),"en attente");
		}
		return Cc;
		
		
	}



	public void setCc(List<Contrat> cc) {
		Cc = cc;
	}



	public String getWord() {
		
		return word;
	}



	public void setWord(String word) {
		this.word = word;
	}


	public List<Contrat> Search(String word) {
		System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

		return conimp.Search(word);
	}




	



	
	
    
}
