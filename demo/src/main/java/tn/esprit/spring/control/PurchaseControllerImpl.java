package tn.esprit.spring.control;



import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.persistence.OneToOne;
import javax.swing.Popup;

import org.apache.catalina.connector.Request;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.controller.ContratRestControlImp;
import tn.esprit.spring.controller.PurchaseRestControlImp;
import tn.esprit.spring.controller.SimpleEmailExampleController;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Purchase;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.BankServiceImp;
import tn.esprit.spring.service.ContratServiceImp;
import tn.esprit.spring.service.IPurchaseService;
import tn.esprit.spring.service.PurchaseServiceImp;
import tn.esprit.spring.service.UserSerivce;
import tn.esprit.spring.service.UserServiceImpl;

@Scope(value = "session")
@Controller(value = "purchaseController")
@ELBeanName(value = "purchaseController")
@Join(path = "/", to = "/login.jsf")
public class PurchaseControllerImpl {
	@Autowired
	IPurchaseService ipurchaseService;
	ContratControllerImp icontratService;
	
	
	PurchaseRestControlImp pur;
	private Long id;
	private float prix;
	private User user;
	private Contrat cont;
	private long selectedbankid;
	public List<Contrat> contrat=new ArrayList<Contrat>();
	public List<Float> listF;
	private float selectedP;
	private String word;
	private List <Purchase> purchases;
	
	public IPurchaseService getIpurchaseService() {
		return ipurchaseService;
	}
	public void setIpurchaseService(IPurchaseService ipurchaseService) {
		this.ipurchaseService = ipurchaseService;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getPrix() {
		prix=ic1.PrixADV();
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Contrat> getContrat() {
		contrat=icontratService.getContrats();
		return contrat;
	}
	public void setContrat(List<Contrat> contrat) {
		this.contrat = contrat;
	}
	public List<Purchase> getPurchases() {
		if (word== null){
		purchases=ipurchaseService.recupérePurchaseByUser(UserControllerImpl.getT().getId()); }
		else{
			purchases=ipurchaseService.SearchP(word);
		}
		return purchases;
	}
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	@Autowired
	ContratServiceImp bs;
	@Autowired
	UserServiceImpl us;
	UserSerivce userService;
	UserControllerImpl uss;
	@Autowired
	SimpleEmailExampleController tmail;
	public void ajouterPurchase()
	{ System.err.println("*********"+this.selectedbankid);
	   this.cont=bs.getContratById(this.selectedbankid);
	   System.err.println("*********"+this.cont.getTitre());
	   System.err.println("USER ACTUEL"+UserControllerImpl.getT());
	Purchase s=new Purchase();
	ipurchaseService.addPurchase(new Purchase(id,prix,UserControllerImpl.getT(),cont));
	bs.updateContratDePurchase(cont);
 //	tmail.sendSimpleEmail(UserControllerImpl.getT().getFirstName());
	}
	public void removePurchase(int employeid)
	{
		ipurchaseService.deletePurchase(employeid);
	}
	public void displayPurchase(Purchase empl)
	{
		this.setPrix((empl.getPrix()));
	/*	this.setLastName(empl.getLastName());
		this.setActif(empl.isActif());
		this.setEmail(empl.getEmail());
		this.setRole(empl.getRole());
		this.setPassword(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId().intValue());
	*/
	}
	
	public PurchaseRestControlImp getPur() {
		return pur;
	}
	public void setPur(PurchaseRestControlImp pur) {
		this.pur = pur;
	}
	public ContratControllerImp getIcontratService() {
		return icontratService;
	}
	public void setIcontratService(ContratControllerImp icontratService) {
		this.icontratService = icontratService;
	}
	public long getSelectedbankid() {
		return selectedbankid;
	}
	public void setSelectedbankid(long selectedbankid) {
		this.selectedbankid = selectedbankid;
	}
	public Contrat getCont() {
		return cont;
	}
	public void setCont(Contrat cont) {
		this.cont = cont;
	}
	@Autowired
	ContratRestControlImp ic;
	public List<Float> getListF() {
		listF=new ArrayList<Float>();
		if(ic.PrixADV()!=0)
		{	listF.add(ic.PrixADV()); }
		
			System.err.println("chaine vide"+ic.PrixADV());
		
		return listF;
	}
	public void setListF(List<Float> listF) {
		this.listF = listF;
	}
	@Autowired
	ContratRestControlImp ic1;
	public float getSelectedP() {
		selectedP=ic1.PrixADV();
		return selectedP;
	}
	public void setSelectedP(float selectedP) {
		this.selectedP = selectedP;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

	@Autowired
	PurchaseServiceImp purs;
	public List<Purchase> Search(String word) {
		System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

		return purs.SearchP(word);
	}
	
	



	
}
