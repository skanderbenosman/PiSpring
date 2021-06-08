package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.service.IAppointmentService;



@RestController
public class AppointmentController {
	@Autowired
	IAppointmentService   appointmentss;
	
	@GetMapping("/lister_date_disponible_byAgent /{id_user}/{id_agent}/{date}")
	public String lister_date_disponible_bygarden(@PathVariable("id_user") int id_user,@PathVariable("id_agent ") int id_agent ,@PathVariable("date") String date) throws ParseException,Exception {
		
		return appointmentss.lister_date_disponible_byAgent(id_user,id_agent ,date);
	}

	

	@PostMapping("/ajouter_agent _rendezVous/{id_user}/{id_agent}")
	public String ajouter_Agent_rendezVous(@PathVariable("id_user") int id_user,@PathVariable("id_agent ") int id_agent ,@RequestBody Appointment appointment) throws ParseException {
		
		return appointmentss.ajouter_Agent_rendezVous(id_user,id_agent ,appointment);
	}
	
	@DeleteMapping("/delete_appointment/{user_id}/{appointment_id}")
	public String delete_appointment(@PathVariable("user_id") int user_id,@PathVariable("appointment_id") int appointment_id) throws ParseException {
		
		return appointmentss.delete_appointment(user_id,appointment_id);
	
	}
	@PutMapping("/update_appointment_By_User/{user_id}/{appointment_id}")
	public String update_appointment_By_User(@PathVariable("user_id") int user_id, @PathVariable  ("appointment_id")int appointment_id,@RequestBody Appointment  appointment) {

		
		return appointmentss.updateappointmentByUser( user_id, appointment);
	}
	
	@GetMapping(value="/listofAppointment")
	@ResponseBody
	public List<Appointment> getAllAppointment() {
		return appointmentss.getAllAppointment();
	}
	@GetMapping(value="/oneapp/{app_id}")
	@ResponseBody
	public Appointment getBillById ( @PathVariable("app_id") int ids) {
		return appointmentss.getBillById(ids) ;
	}
	@PutMapping("/update_appointment_By_Agent /{agent_id}/{appointment_id}")
	public String update_appointment_By_Agent (@PathVariable("agent _id") int agent_id, @PathVariable int appointment_id,@RequestBody Appointment  appointment) {

		
		return appointmentss.updateappointmentByUser( agent_id , appointment);
	}
	


	@PostMapping("/accepte_appointment/{id_agent}/{appointment_id}")
	public String accepte_appointment(@PathVariable("id_agent") int id_agent,@PathVariable("appointment_id") int appointment_id) throws ParseException {
		
		return appointmentss.delete_appointment(id_agent,appointment_id);
	
	}
	@PostMapping("/refut_appointment/{id_agent}/{appointment_id}")
	public String refut_appointment(@PathVariable("id_agent") int id_agent,@PathVariable("appointment_id") int appointment_id) throws ParseException {
		
		return appointmentss.delete_appointment(id_agent,appointment_id);
	
	}
	@GetMapping("/searchappointment/{user_id}/{search}")
	public List<Appointment> searchappointment(@PathVariable("user_id") int user_id, @PathVariable String search) {

		return appointmentss.searchappointment(user_id, search);
	}
	@GetMapping("/getallappointment_status_1/{id_agent}")
	public List<Appointment> getallappointment_status_1(@PathVariable("id_agent") int id_agent) throws ParseException {
	
		return appointmentss.getallappointment_status_1(id_agent);
	}
	
	@GetMapping("/findClientAppointment/{user_id}")
	public  List<Appointment> findClientAppointment(@PathVariable("user_id") int user_id) {

		return appointmentss.findClientAppointment(user_id);
	}

	
	
}
