package dari.tn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Subscribe")

public class Subscribe implements Serializable {


    /**
	 * kk
	 */
	private static final long serialVersionUID = 46463834952660110L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sub_id;

    @ManyToOne
    @JoinColumn(name = "subscription_id",  nullable = false)
    private Subscription subscription;
    
    @ManyToOne
    @JoinColumn(name = "utilisateurId",   nullable = false)
    private Utilisateur utilisateur ;
    
    @Column
    private Date DateD;
    @Column
    private Date DateF ;
    @Column
    private boolean paid ;
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Date getDateD() {
		return DateD;
	}
	public void setDateD(Date dateD) {
		DateD = dateD;
	}
	public Date getDateF() {
		return DateF;
	}
	public void setDateF(Date dateF) {
		DateF = dateF;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Subscribe(Subscription subscription, Utilisateur utilisateur, Date dateD, Date dateF, boolean paid) {
		super();
		this.subscription = subscription;
		this.utilisateur = utilisateur;
		DateD = dateD;
		DateF = dateF;
		this.paid = paid;
	}
	public Subscribe() {
		super();
	}
    
   
    
    
}
