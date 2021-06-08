package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.AppointmentType;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.IAppointmentService;
import tn.esprit.spring.service.UserService;

@Controller(value ="appController")
@ELBeanName(value ="appController")
@Join(path = "/Appointment", to = "/AddAppointment.jsf")
@Component
public class AppointmentControllerjsf {
	@Autowired
	IAppointmentService servapp;
	
	@Autowired
	UserService userService;
	private int id;
	private int appointmentIdToupdate;
	private String description;

	private Date appointmentdate;
	
	private String beginhour;
	
	private String endhour;
	
	private int Status;
	private AppointmentType appointmentType;
	private User user;

	private User agent;

	private int id_agent=4;
	
	
	

	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Date getAppointmentdate() {
		return appointmentdate;
	}
	public void setAppointmentdate(Date appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public AppointmentType[] getAppointmentTypes(){
		return AppointmentType
				.values();
	}
    public int getAppointmentIdToupdate() {
		return appointmentIdToupdate;
	}
	public void setAppointmentIdToupdate(int appointmentIdToupdate) {
		this.appointmentIdToupdate = appointmentIdToupdate;
	}

	public int getId_agent() {
		return id_agent;
	}
	public void setId_agent(int id_agent) {
		this.id_agent = id_agent;
	}

	public IAppointmentService getServapp() {
		return servapp;
	}
	public void setServapp(IAppointmentService servapp) {
		this.servapp = servapp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descr) {
		description = descr;
	}

	public String getBeginhour() {
		return beginhour;
	}
	public void setBeginhour(String begin) {
		beginhour = begin;
	}
	public String getEndhour() {
		return endhour;
	}
	public void setEndhour(String end) {
		endhour = end;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getAgent() {
		return agent;
	}
	public void setAgent(User agent) {
		this.agent = agent;
	}
	public AppointmentType getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}
    
public 	List<Appointment> getAllAppointment()
{
	return servapp.getAllAppointment();
}
	
public String ajouterAgentrendezVous()
{
	User u = new User();
	u=userService.findOne(LoginController.userDetails.getId());
	Appointment A= new Appointment( description,appointmentdate, beginhour,endhour,Status,
		 appointmentType);
	
	return	servapp.ajouter_Agent_rendezVous(u.getId().intValue(),id_agent,A);
	
}
public String delete_appointment(int id)
{
	User u = new User();
	u=userService.findOne(LoginController.userDetails.getId());
	return servapp.delete_appointment(u.getId().intValue() ,id);
}
public String update_appointment_By_User()
{	User u = new User();
u=userService.findOne(LoginController.userDetails.getId());
	Appointment A= new Appointment(appointmentIdToupdate,description,appointmentdate, beginhour,endhour,Status,
			 appointmentType);
	return servapp.updateappointmentByUser(u.getId().intValue(),A);	
}
public String accepte_appointment(int id_appointment) {
	return servapp.accepte_appointment(id_appointment, id_agent);
}
 
public List<Appointment> FindAppointmentsByUser()
{
	return servapp.FindAppointmentsByUser(LoginController.userDetails.getId());	
}
public void displayAppointment(Appointment A)
{
this.setAppointmentdate(A.getDate());
this.setAppointmentIdToupdate(A.getId());
this.setAppointmentType(A.getAppointmentType());
this.setBeginhour(A.getBeginhour());
this.setDescription(A.getDescription());
this.setEndhour(A.getEndhour());

}



}
