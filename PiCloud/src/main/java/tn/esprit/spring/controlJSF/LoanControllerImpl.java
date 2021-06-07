package tn.esprit.spring.controlJSF;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.controller.LoanRestControllerImp;
import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Loan;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.BankServiceImp;
import tn.esprit.spring.service.IBankService;
import tn.esprit.spring.service.ILoanService;
import tn.esprit.spring.service.LoanServiceImp;

@Scope(value = "session")
@Controller(value = "loanController")
@ELBeanName(value = "loanController")
@Join(path = "/", to = "/login.jsf")
public class LoanControllerImpl {
	@Autowired
	ILoanService iloanService;
	IBankService ibankService;
	
	private Long id;
	private final double InterestRate=0.8;
	private int LoanTerm;
	private int LoanAmount;
	private int MonthlyIncome;
	private User user;
	private Bank bank;
	private List<Bank> banks;
	private String selectedbank;
	private long selectedbankid;
	private String word ;
	private List <Loan> Loans;

	
	public LoanControllerImpl() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	public ILoanService getIloanService() {
		return iloanService;
	}
	public void setIloanService(ILoanService iloanService) {
		this.iloanService = iloanService;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getLoanTerm() {
		return LoanTerm;
	}
	public void setLoanTerm(int loanTerm) {
		LoanTerm = loanTerm;
	}
	public int getLoanAmount() {
		return LoanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		LoanAmount = loanAmount;
	}
	public int getMonthlyIncome() {
		return MonthlyIncome;
	}
	public void setMonthlyIncome(int monthlyIncome) {
		MonthlyIncome = monthlyIncome;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public List<Loan> getLoans() {
		if (word== null){
		Loans=iloanService.retrieveAllLoan(); }
		else{
			Loans=iloanService.SearchL(word);
		}
		return Loans;
	}
	public void setLoans(List<Loan> loans) {
		Loans = loans;
	}
	public void removeLoan(int employeid)
	{
		iloanService.deleteLoan(employeid);
	}
	public double getInterestRate() {
		return InterestRate;
	}
	
	@Autowired
	BankServiceImp bs;
	@Autowired
	LoanServiceImp lo;
	public void ajouterLoan()
	{  System.err.println("*********"+this.selectedbankid);
	   this.bank=bs.getBankById(this.selectedbankid);
	   System.err.println("*********"+this.bank.getNameBank());
	   System.err.println("loan ::::"+id);
	   iloanService.addLoan(new Loan(id, LoanTerm, LoanAmount, MonthlyIncome, UserControllerImpl.getT(), bank));
	//	idEvent=ibankService.
		//iloanService.affecterBankLouer(String.valueOf(id), "41");
	}
	public String getSelectedbank() {
		
		return selectedbank;
	}
	public void setSelectedbank(String selectedbank) {
		this.selectedbank = selectedbank;
	}

	
	public float calculloan(Loan u){
	float q=u.getLoanAmount();
		return (Float) q;
	}
	public IBankService getIbankService() {
		return ibankService;
	}
	public void setIbankService(IBankService ibankService) {
		this.ibankService = ibankService;
	}
	public List<Bank> getBanks() {
	//	banks=ibankService.retrieveAllBank();
		return banks;
	}
	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}
	public long getSelectedbankid() {
		return selectedbankid;
	}
	public void setSelectedbankid(long selectedbankid) {
		this.selectedbankid = selectedbankid;
	}
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	//calcul 
    public float calcL(long id){
		return lo.calculLoan(id);
    	
    }
	
    public float calcLMonthly(long id){
		return lo.calculLoanMonthly(id);
    	
    }
    
    public float calcLMonthlyMax(long id){
		return lo.calculLoanMonthlyMax(id);
    	
    }
    
    public List<Loan> Search(String word) {
		System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

		return lo.SearchL(word);
	}
    
	public void displayLoan(Loan empl)
	{ System.err.println("display***"+empl.getId());
		this.setId(empl.getId());
		this.setLoanTerm(empl.getLoanTerm());
		this.setLoanAmount(empl.getLoanAmount());
		this.setMonthlyIncome(empl.getMonthlyIncome());
		this.setBank(bs.getBankById(empl.getBank().getId()));
	}
	
	public void update()
	{ 
		//this.setId(empl.getId());
		iloanService.updateLoan(new Loan(id,LoanTerm,LoanAmount,MonthlyIncome,UserControllerImpl.getT(),bank));
		
	}
	public String TestUpdate(Loan empl){
		displayLoan(empl);
		return "/pages/admin/EditLoan.xhtml?faces-redirect=true";
		
	}
    
}
