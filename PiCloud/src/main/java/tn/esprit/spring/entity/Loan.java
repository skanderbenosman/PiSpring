package tn.esprit.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Loan")
public class Loan {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private final double InterestRate=0.8;
	private int LoanTerm;
	private int LoanAmount;
	private int MonthlyIncome;
	@OneToOne
	private User user;
	@OneToOne
	private Bank bank;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	

	public Loan(Long id, int loanTerm, int loanAmount, int monthlyIncome, User user, Bank bank) {
		super();
		this.id = id;
		this.LoanTerm = loanTerm;
		this.LoanAmount = loanAmount;
		this.MonthlyIncome = monthlyIncome;
		this.user = user;
		this.bank = bank;
	}
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public double getInterestRate() {
		return InterestRate;
	}
	@Override
	public String toString() {
		return "Loan [id=" + id + ", InterestRate=" + InterestRate + ", LoanTerm=" + LoanTerm + ", LoanAmount="
				+ LoanAmount + ", MonthlyIncome=" + MonthlyIncome + ", user=" + user + ", bank=" + bank + "]";
	}
	
	
	
	
}
