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
    private int subscribe_id;

    @ManyToOne
    @JoinColumn(name = "subscription_id",  nullable = false)
    private Subscription subscription;
    
    @Column
    private Date DateD;
    @Column
    private Date DateF ;
    @Column
    private boolean paid ;
    
    
	public Subscribe() {
	}
	public Subscribe(int subscribe_id, Subscription subscription, Date dateD, Date dateF, boolean paid) {
		this.subscribe_id = subscribe_id;
		this.subscription = subscription;
		DateD = dateD;
		DateF = dateF;
		this.paid = paid;
	}
	public int getSubscribe_id() {
		return subscribe_id;
	}
	public void setSubscribe_id(int subscribe_id) {
		this.subscribe_id = subscribe_id;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
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
	@Override
	public String toString() {
		return "Subscribe [subscribe_id=" + subscribe_id + ", subscription=" + subscription + ", DateD=" + DateD
				+ ", DateF=" + DateF + ", paid=" + paid + "]";
	}

    
    
}
