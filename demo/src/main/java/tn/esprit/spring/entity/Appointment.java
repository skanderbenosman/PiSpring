package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String Description;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String Beginhour;
	
	private String Endhour;
	
	private int Status;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private User agent;
	@Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;
	
	public Appointment () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public String getBeginhour() {
		return Beginhour;
	}

	public void setBeginhour(String beginhour) {
		Beginhour = beginhour;
	}

	public String getEndhour() {
		return Endhour;
	}

	public void setEndhour(String endhour) {
		Endhour = endhour;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 public AppointmentType getAppointmentType() {
	        return appointmentType;
	    }

	    public void setAppointmentType(AppointmentType appointmentType) {
	        this.appointmentType = appointmentType;
	    }

		public Appointment(int id, String description, Date date, String beginhour, String endhour, int status,
				User user, User agent, AppointmentType appointmentType) {
			super();
			this.id = id;
			Description = description;
			this.date = date;
			Beginhour = beginhour;
			Endhour = endhour;
			Status = status;
			this.user = user;
			this.agent = agent;
			this.appointmentType = appointmentType;
		}

		public Appointment(String description, Date date, String beginhour, String endhour, int status, User user,
				User agent, AppointmentType appointmentType) {
			super();
			Description = description;
			this.date = date;
			Beginhour = beginhour;
			Endhour = endhour;
			Status = status;
			this.user = user;
			this.agent = agent;
			this.appointmentType = appointmentType;
		}

		public Appointment(String description, Date date, String beginhour, String endhour, int status,
				AppointmentType appointmentType) {
			super();
			Description = description;
			this.date = date;
			Beginhour = beginhour;
			Endhour = endhour;
			Status = status;
			this.appointmentType = appointmentType;
		}

		public Appointment(int id, String description, Date date, String beginhour, String endhour, int status,
				AppointmentType appointmentType) {
			super();
			this.id = id;
			Description = description;
			this.date = date;
			Beginhour = beginhour;
			Endhour = endhour;
			Status = status;
			this.appointmentType = appointmentType;
		}
 
		
}
