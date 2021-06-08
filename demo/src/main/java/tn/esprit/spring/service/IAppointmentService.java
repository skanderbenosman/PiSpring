package tn.esprit.spring.service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import tn.esprit.spring.entity.Appointment;


public interface IAppointmentService {

	public	List<Appointment> getAllAppointment();

	public	List<Appointment> FindDateAppointmentAgent(Date date, int agent_id);

	public	List<Appointment> SearchApp(String search);

	public List<Appointment> FindAppointmentsByUser(Long parent_id);

	public	String lister_date_disponible_byAgent(int id_user, int id_agent, String date) throws ParseException;
	public String lister_date_disponible_byagent(int user_id,int id_agent,String date) throws ParseException  ;

	public String ajouter_Agent_rendezVous(int id_user,int id_agent, Appointment appointment);

	public String delete_appointment(int user_id, int appointment_id);

	public String updateappointmentByUser(int user_id, Appointment appointment);
	
	public	Appointment getBillById(int ids);

	public	List<Appointment> searchappointment(int user_id, String search);

	public	List<Appointment> getallappointment_status_1(int id_agent);

	public	List<Appointment> findClientAppointment(int user_id);
	public String ajouter_agent_rendezVous(int id_user , int id_agent,Appointment appointment) ;
	public String accepte_appointment(int id_appointment ,int agent_id  );


}
