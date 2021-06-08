package tn.esprit.spring.control;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.DemandeLouer;
import tn.esprit.spring.repository.AdvertisementRepository;
import tn.esprit.spring.repository.DemandeLouerRepository;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller(value = "demandLouerList")
@ELBeanName(value = "demandLouerList")
@Join(path = "/advertisement", to = "/advertisement.jsf")
@Scope(value = "session")

@Component
public class DemandeLouerController {

    @Autowired
    private DemandeLouerRepository demandeLouerRepository;
    @Autowired
    private AdvertisementRepository advertisementService;
    
    private List<DemandeLouer> demandeLouers;
    private List<DemandeLouer> demandeLouerList;
    private List<DemandeLouer> demandes;
    private List<DemandeLouer> demandesfiltred;
    
    private DemandeLouer demandeLouer;
    
	private Long annid ;
	public Long[] annids() { 
		
		//Listannonces = advertisementService.findAllByType(Advertisement.Type.Rent).getid();
		return null; 
	}
    
    private long annonceid;
    Date dbb;
    Date dff;
    String done;
    
    
    
    public long getAnnonceid() {
		return annonceid;
	}

	public void setAnnonceid(long annonceid) {
		this.annonceid = annonceid;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public Date getDbb() {
		return dbb;
	}

	public void setDbb(Date dbb) {
		this.dbb = dbb;
	}

	public Date getDff() {
		return dff;
	}

	public void setDff(Date dff) {
		this.dff = dff;
	}

    public DemandeLouer getDemandeLouer() {
        return demandeLouer;
    }
    

    public void setDemandeLouer(DemandeLouer demandeLouer) {
		this.demandeLouer = demandeLouer;
	}

	public void addDemandeLouer(DemandeLouer demandeLouer) {
        this.demandeLouer = demandeLouer;
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('dlg2').show();");
    }

    public List<DemandeLouer> getDemandeLouerList() {

        return demandeLouerList;
    }

    public void setDemandeLouerList(List<DemandeLouer> demandeLouerList) {
        this.demandeLouerList = demandeLouerList;
    }
    
    

    public List<DemandeLouer> getDemandes() {
    	demandes = demandeLouerRepository.findAll();

		return demandes;
	}

	public void setDemandes(List<DemandeLouer> demandes) {
		
		this.demandes = demandes;
	}

	public List<DemandeLouer> getDemandesfiltred() {
		return demandesfiltred;
	}

	public void setDemandesfiltred(List<DemandeLouer> demandesfiltred) {
		this.demandesfiltred = demandesfiltred;
	}

	/*@Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        List<DemandeLouer> allByUser = demandeLouerRepository.findAllByUser(LoginController.userDetails.getId());
        demandeLouers = allByUser.stream()
                .distinct()
                .collect(Collectors.toList());
    }*/
	@RequestAction
    @IgnorePostback
    public void loadData() {
		demandes = demandeLouerRepository.findAll();
    }

    public List<DemandeLouer> getDemandeLouers() {
        System.out.println("------------------------------------------------------------------");
        List<DemandeLouer> allByUser = demandeLouerRepository.findAllByUser(LoginController.userDetails.getId());
        demandeLouers = allByUser.stream()
                .distinct()
                .collect(Collectors.toList());
        return demandeLouers;
    }

    public void setDemandeLouers(List<DemandeLouer> demandeLouers) {
        this.demandeLouers = demandeLouers;
    }

    @Transactional
    public void deleteDemande(long user, Advertisement annonce) {
        System.out.println(user);
        System.out.println(annonce);
        demandeLouerRepository.deleteAllByUserAndAnnonce(user, annonce);
        loadData();
    }
    
    @Transactional
	//int iduser, int idannonce, Date db, Date df
    public String louer2(Long iduser,Long idannonce,Date dbb, Date dff) {
        //System.out.println(advertisementService.findById(Long.valueOf(idannonce)).get());
		//Long iduser = (long) 1;
		//Long idannonce = (long) 1;
       // demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
       // return "acceuil.xhtml?faces-redirect=true";
       // demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
      // DemandeLouer d= new DemandeLouer( iduser,db,df,advertisementService.findById(Long.valueOf(idannonce)).get());
		//demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
		//Date dbb= new Date();
		//Date dff=new Date();
    	
		demandeLouerRepository.save(new DemandeLouer(null,iduser,dbb,dff,"non_traitee",advertisementService.findById(Long.valueOf(idannonce)).get()));

		//  demandeLouerRepository.save(d);
       // demandeLouerRepository.save(new DemandeLouer(null, iduser, df, df, null, advertisementService.findById(Long.valueOf(idannonce)).get()));
		
		/*Long idannonce=(long) 1; 
		Advertisement annonce=advertisementService.findById(Long.valueOf(idannonce)).get();
		DemandeLouer d= new DemandeLouer(1, dff, dbb, annonceModif);*/
		
		//demandeLouerRepository.save
		System.out.println("Votre Demande est envoyée et sera traitée bientot");
        //return "Votre Demande est Envoyée et sera traitée bientot";
		done= "Votre Demande est envoyée et sera traitée bientot";
		return "annoncerequestalt.xhtml?faces-redirect=true";
		
		//return "Votre Demande est envoyée et sera traitée bientot"
    
    }
    
   
    public void approuvedemand(long id ){
    	         
    	
    	DemandeLouer d=new DemandeLouer();
    	d = demandeLouerRepository.retrievedemandById(id);
    	
    	d.setDecision("Demande_Approuvee");
    	demandeLouerRepository.save(d);
    	
    	
    }
    public void refusedemand(long id){
    	DemandeLouer d=new DemandeLouer();
    	d = demandeLouerRepository.retrievedemandById(id);
    	d.setDecision("Demande_Refusee");
    	demandeLouerRepository.save(d);
    }
    public void deletedemand(long id){
    	demandeLouerRepository.deleteById(id);
    	
    }
}
