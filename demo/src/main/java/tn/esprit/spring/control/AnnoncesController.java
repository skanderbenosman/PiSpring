package tn.esprit.spring.control;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.DemandeLouer;
import tn.esprit.spring.entity.UserFavoris;
import tn.esprit.spring.repository.AdvertisementRepository;
import tn.esprit.spring.repository.DemandeLouerRepository;
import tn.esprit.spring.repository.UserFavorisRepository;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

@Controller(value = "annonceList")
@ELBeanName(value = "annonceList")
@Join(path = "/advertisement", to = "/advertisement.jsf")
@Component
public class AnnoncesController {
    @Autowired
    private AdvertisementRepository advertisementService;

    @Autowired
    private DemandeLouerRepository demandeLouerRepository;
    @Autowired
    private UserFavorisRepository userFavorisRepository;
    private List<Advertisement> annonces;
    private List<Advertisement> annoncesFiltred;
    private Advertisement annonceModif;

    public Advertisement getAnnonceModif() {
        return annonceModif;

    }

    public void setAnnonceModif(Advertisement annonceModif) {
        this.annonceModif = annonceModif;
    }
    public String redirect(Advertisement advertisement){
        this.annonceModif = advertisement;
        return "modifyAnnonce.xhtml?faces-redirect=true";
    }
    public String modify(Advertisement advertisement){
        advertisementService.save(advertisement);
        return "listeAnnonce.xhtml?faces-redirect=true";
    }
    public List<Advertisement> getAnnoncesFiltred() {
        return annoncesFiltred;
    }

    public void setAnnoncesFiltred(List<Advertisement> annoncesFiltred) {
        this.annoncesFiltred = annoncesFiltred;
    }

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        annonces = advertisementService.findAllByType(Advertisement.Type.Rent);
    }

    	
    @Transactional
    public String louer(int iduser, int idannonce) {
        System.out.println(advertisementService.findById(Long.valueOf(idannonce)).get());
      //demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
      //originale code  // return "acceuil.xhtml?faces-redirect=true";
     // new added 
        //return "requestannonce.xhtml?faces-redirect=true";
        return "annoncerequestalt.xhtml?faces-redirect=true";
    }
    
    
    Date dbb;
    Date dff;
    
    
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

	@Transactional
	//int iduser, int idannonce, Date db, Date df
    public String louer2() {
        //System.out.println(advertisementService.findById(Long.valueOf(idannonce)).get());
		Long iduser = (long) 1;
		Long idannonce = (long) 1;
       // demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
       // return "acceuil.xhtml?faces-redirect=true";
       // demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
      // DemandeLouer d= new DemandeLouer( iduser,db,df,advertisementService.findById(Long.valueOf(idannonce)).get());
		//demandeLouerRepository.save(new DemandeLouer(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
		Date dbb= new Date();
		Date dff=new Date();
		demandeLouerRepository.save(new DemandeLouer(null,iduser,dbb,dff,null,advertisementService.findById(Long.valueOf(idannonce)).get()));

		//  demandeLouerRepository.save(d);
       // demandeLouerRepository.save(new DemandeLouer(null, iduser, df, df, null, advertisementService.findById(Long.valueOf(idannonce)).get()));
		
		/*Long idannonce=(long) 1; 
		Advertisement annonce=advertisementService.findById(Long.valueOf(idannonce)).get();
		DemandeLouer d= new DemandeLouer(1, dff, dbb, annonceModif);*/
		
		//demandeLouerRepository.save
		System.out.println("Votre Demande est envoyée et sera traitée bientot");
        //return "Votre Demande est Envoyée et sera traitée bientot";
		return "annoncerequestalt.xhtml?faces-redirect=true";
    
    }

    @Transactional
    public String addFavoris(int iduser, int idannonce) {
        userFavorisRepository.save(new UserFavoris(null, iduser, advertisementService.findById(Long.valueOf(idannonce)).get()));
        return "acceuil.xhtml?faces-redirect=true";
    }

    public List<Advertisement> getAnnonces() {
        annonces = advertisementService.findAllByType(Advertisement.Type.Rent);
        System.out.println(annonces);
        return annonces;
    }
    public void deleteAnnonce(Advertisement advertisement){
        this.advertisementService.delete(advertisement);
    }

    public void setAnnonces(List<Advertisement> annonces) {
        this.annonces = annonces;
    }
}
