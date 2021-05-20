package dari.tn.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public  class Utilisateur implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 537136081490133106L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilisateurId;

    private String lastName;
    private String firstName;
    private String password;
    private String phone;
    private Region Region;
    private String email;
    private Instant created;
    private boolean subscribed;
    public SubType SubscriptionType;
	public Long getUtilisateurId() {
		return utilisateurId;
	}
	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Region getRegion() {
		return Region;
	}
	public void setRegion(Region region) {
		Region = region;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Instant getCreated() {
		return created;
	}
	public void setCreated(Instant created) {
		this.created = created;
	}
	public boolean isSubscribed() {
		return subscribed;
	}
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	public SubType getSubscriptionType() {
		return SubscriptionType;
	}
	public void setSubscriptionType(SubType subscriptionType) {
		SubscriptionType = subscriptionType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Utilisateur(Long utilisateurId, String lastName, String firstName, String password, String phone,
			dari.tn.model.Region region, String email, Instant created, boolean subscribed, SubType subscriptionType) {
		super();
		this.utilisateurId = utilisateurId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.phone = phone;
		Region = region;
		this.email = email;
		this.created = created;
		this.subscribed = subscribed;
		SubscriptionType = subscriptionType;
	}
	public Utilisateur() {
		super();
	}


}
