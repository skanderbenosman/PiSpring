package tn.esprit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class DemandeLouer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private long user;
	
	@Column 
	private Date datedebut;
	@Column 
	private Date datefin;
	@Column 
	private String decision;
    @OneToOne
	private Advertisement annonce;
    
   
	public DemandeLouer(Object object, Long iduser, Date dbb, Date dff, Object object2, Advertisement advertisement) {
		this.id=(Long) object;
		this.user=iduser;
		this.datedebut=dbb;
		this.datefin=dff;
		this.decision="non_traitee";
		this.annonce=advertisement;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DemandeLouer that = (DemandeLouer) o;
		return user == that.user && Objects.equals(annonce, that.annonce);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, annonce);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Advertisement getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Advertisement annonce) {
		this.annonce = annonce;
	}

	public DemandeLouer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}
