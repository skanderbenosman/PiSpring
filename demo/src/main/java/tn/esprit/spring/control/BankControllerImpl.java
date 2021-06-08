package tn.esprit.spring.control;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.BankServiceImp;
import tn.esprit.spring.service.IBankService;


@Scope(value = "session")
@Controller(value = "bankController")
@ELBeanName(value = "bankController")
@Join(path = "/", to = "/login.jsf")
public class BankControllerImpl {
	@Autowired
	IBankService ibankService;
	private Long id;
	private Boolean loggedIn;
	private String NameBank;
	public List <Bank> Banks;
	public String bank;
	public Long selectedbankid;
	private String word;
	public BankControllerImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IBankService getIbankService() {
		return ibankService;
	}
	public void setIbankService(IBankService ibankService) {
		this.ibankService = ibankService;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameBank() {
		return NameBank;
	}
	public void setNameBank(String nameBank) {
		NameBank = nameBank;
	}
	public List<Bank> getBanks() {
		if (word== null){
			Banks=ibankService.retrieveAllBank();
		} else{
			Banks=ibankService.Search(word);
		}
		
		return Banks;
	}
	public void setBanks(List<Bank> banks) {
		Banks = banks;
	}
	
	public void removeBank(int employeid)
	{
		ibankService.deleteBank(employeid);
	}
	public void ajouterBank()
	{
		ibankService.addBank(new Bank(NameBank));
	}
	public void displayBank(Bank empl)
	{ 
		this.setId(empl.getId());
		this.setNameBank(empl.getNameBank());
		
	}
	public void update()
	{ 
		//this.setId(empl.getId());
		ibankService.updateBank(new Bank(id,NameBank));
		
	}
	public String Redirection() {
		String navigateTo = "null";
		
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			loggedIn = true; 
	
		return navigateTo;}

	public String goToPage1() {
	    // ...
	    return "test.xhtml";
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Long getSelectedbankid() {
		return selectedbankid;
	}
	public void setSelectedbankid(Long selectedbankid) {
		this.selectedbankid = selectedbankid;
	}
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}


	@Autowired
	UserControllerImpl us;
	public String TestUpdate(Bank empl){
		displayBank(empl);
		return "/pages/admin/EditBank.xhtml?faces-redirect=true";
		
	}
	
	@Autowired
	BankServiceImp bks1;
	public List<Bank> Search(String word) {
		System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

		return bks1.Search(word);
	}

	
}
