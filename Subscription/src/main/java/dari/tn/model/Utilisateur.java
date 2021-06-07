package dari.tn.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;


@Entity  
@Table(name = "Utilisateur")
public class Utilisateur implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -9053198679814003794L;
@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private int user_id;
@Column
private String name;
@Column
private int credit;
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getCredit() {
	return credit;
}
public void setCredit(int credit) {
	this.credit = credit;
}
public Utilisateur(int user_id, String name, int credit) {
	super();
	this.user_id = user_id;
	this.name = name;
	this.credit = credit;
}
public Utilisateur() {
	super();
}




}