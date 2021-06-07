package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bank")
public class Bank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String NameBank;
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
	public Bank( String nameBank) {
		super();

		this.NameBank = nameBank;
	}
	@Override
	public String toString() {
		return "" + NameBank + "";
	}
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bank(Long id, String nameBank) {
		super();
		this.id = id;
		NameBank = nameBank;
	}

	
}
