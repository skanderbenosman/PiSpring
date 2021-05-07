package dari.tn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity  
@Table(name = "Subscription")
public class Subscription implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 7098890234731393759L;
@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private int subscription_id;
@Column
private String subscription_offer;
@Column
private String subscription_title;
@Column
private int subscription_price;
public Subscription() {
}


public Subscription(String subscription_offer, String subscription_title, int subscription_price) {
	super();
	this.subscription_offer = subscription_offer;
	this.subscription_title = subscription_title;
	this.subscription_price = subscription_price;
}

@OneToMany(mappedBy = "subscription")
private List<Subscribe> subscribes ;


public int getSubscription_id() {
	return subscription_id;
}
public void setSubscription_id(int subscription_id) {
	this.subscription_id = subscription_id;
}
public String getSubscription_offer() {
	return subscription_offer;
}
public void setSubscription_offer(String subscription_offer) {
	this.subscription_offer = subscription_offer;
}
public String getSubscription_title() {
	return subscription_title;
}
public void setSubscription_title(String subscription_title) {
	this.subscription_title = subscription_title;
}
public int getSubscription_price() {
	return subscription_price;
}
public void setSubscription_price(int subscription_price) {
	this.subscription_price = subscription_price;
}
@Override
public String toString() {
	return "Subscription [subscription_id=" + subscription_id + ", subscription_offer=" + subscription_offer
			+ ", subscription_title=" + subscription_title + ", subscription_price=" + subscription_price + "]";
}


}
