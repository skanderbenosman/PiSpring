package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Contrat")
public class Contrat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
    private String titre;
    private String description;
    private Date dateS;
    private String status="en attente";
    @OneToOne
    private Advertisement advertisement;
    private String UserName;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateS() {
		return dateS;
	}
	public void setDateS(Date dateS) {
		this.dateS = dateS;
	}


	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	public Contrat(Long id, String titre, String description, Date dateS, Advertisement advertisement,
			String userName) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateS = dateS;
		this.advertisement = advertisement;
		UserName = userName;
	}
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Contrat [id=" + id + ", titre=" + titre + ", description=" + description + ", dateS=" + dateS
				+ ", status=" + status + ", advertisement=" + advertisement + ", UserName=" + UserName + "]";
	}
	
    
}
