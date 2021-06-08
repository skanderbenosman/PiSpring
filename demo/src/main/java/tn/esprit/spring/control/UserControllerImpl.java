package tn.esprit.spring.control;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Component;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.UserSerivce;

@Scope(value = "session")
@Controller(value = "userController") // Name of the bean in Spring IoC
@ELBeanName(value = "userController") // Name of the bean used by JSF
@Join(path = "/", to = "/login.jsf")
public class UserControllerImpl {
	@Autowired
	UserSerivce userService;

	private String login; private String password; private User user; private static User authenticatedUser ;
	private String firstName; private String lastName; private String email;
	private boolean actif; private Role role;
	private Boolean loggedIn;
	private List<User> employes;
	private Integer employeIdToBeUpdated;
	public static User t;
	

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}
	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
	}
	public void setEmployes(List<User> employes) {
		this.employes = employes;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getAuthenticatedUser() {
		return authenticatedUser;
	}
	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String doLogin() {
		String navigateTo = "null";
		authenticatedUser= userService.authenticate(login , password);
		if (authenticatedUser != null && authenticatedUser.getRole()==Role.ADMINISTRATEUR ) 
		{    
		      User t=this.authenticatedUser;
		      System.err.println("*********"+t);
			navigateTo = "/pages/admin/dashboard.xhtml?faces-redirect=true";
			loggedIn = true; }
		else {
			FacesMessage facesMessage =

					new FacesMessage("Login Failed: please check your username/password and try again.");

			FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";}
	
	public String directionContrat() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/gestionContrat.xhtml?faces-redirect=true";}
	public String directiontest() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/dashboard.xhtml?faces-redirect=true";}
	
	public String directionPurchase() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/AffichePurchase.xhtml?faces-redirect=true";}
	
	public String directionAjoutPurchase() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/AjouterPurchase.xhtml?faces-redirect=true";}
	
	public String redirection() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/AfficheBank.xhtml?faces-redirect=true";}
	
	public String directionLoan() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/AfficheLoan.xhtml?faces-redirect=true";}
	
	public String directionAjoutLoan() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/AajouterLoan.xhtml?faces-redirect=true";}
	
	public String directionEditBank() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/EditBank.xhtml?faces-redirect=true";}
	
	public String directionAfficheAds() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/admin/Ads.xhtml?faces-redirect=true";}
	
	public Role[] getRoles() { return Role.values(); }
	public String addEmploye() {
		String navigateTo ="null";
		if (authenticatedUser==null || !loggedIn) return "/login.xhtml";
		else {userService.addOrUpdateEmploye(new User(email, actif,password,firstName,lastName,role));
	}
	    return navigateTo;}
	public List<User> getEmployes() {
		employes = userService.retrieveAllUsers();
		return employes;
	}
	public void removeEmploye(int employeId)
	{
		userService.deleteUser(employeId);
	}
	public void displayEmploye(User empl)
	{
		this.setFirstName(empl.getFirstName());
		this.setLastName(empl.getLastName());
		this.setActif(empl.isActif());
		this.setEmail(empl.getEmail());
		this.setRole(empl.getRole());
		this.setPassword(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId().intValue());
	}
	public void updateEmploye()
	{ Long id=Long.valueOf(employeIdToBeUpdated);

	userService.addOrUpdateEmploye(new User(id,email, actif,password,
			firstName,lastName ,role)); }
	public static User getT() {
	
		return authenticatedUser;
	}
	public void setT(User t) {
		UserControllerImpl.t = t;
	}
}
