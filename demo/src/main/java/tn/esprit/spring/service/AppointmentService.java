package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.UserRepository;



@Service
public class AppointmentService implements IAppointmentService {
	@Autowired
	AppointmentRepository appointments ;
	@Autowired
	UserRepository users ;
	
	

	@Override
	public List<Appointment> getAllAppointment() {
		return (List<Appointment>) appointments.findAll();
	}
public Appointment  getBillById (int ids){
		
		Optional<Appointment> optionalProduct = appointments.findById(ids);
		return optionalProduct.get();
	}
@Override
	public String ajouter_agent_rendezVous(int id_user , int id_agent,Appointment appointment) {
		User agentId = users.findById(id_agent);
		User UserId = users.findById(id_user);
		if (!agentId.getRoles().toString().contains("ROLE_AGENT")) { return ("id_agent n'est pas valide");	}
		if (UserId.getRoles().toString().contains("ROLE_USER")) {
			
			
	List<String> compteur= new ArrayList<String>();
	for (Appointment appointment1 : appointments.FindDateAppointmentAgent(appointment.getDate(),agentId.getId().intValue())) {
				compteur.add(appointment1.getBeginhour());
			}
				if(compteur.contains(appointment.getBeginhour()))
				{
				return	("hour existe");

				}
				else 
				{ 
					
					
				appointment.setAgent(agentId);
				appointment.setUser(UserId);
			    appointment.setStatus(0);
	           appointment.setEndhour(Integer.toString(Integer.parseInt(appointment.getBeginhour()) +1));
					 Appointment appointment2 = appointments.save(appointment);
			      return ("   " +appointment2);
				
				}
}
			
				else
				{
					return ("user n'est pas client");
					}
		}
@Override
	public String lister_date_disponible_byagent(int user_id,int id_agent,String date) throws ParseException  {
		
		
		User agent = users.findById(id_agent);
		User UserId = users.findById(user_id);
		if (!agent.getRoles().toString().contains("ROLE_AGENT")){
			return ("id_agent n'est pas valide");
		}
		if (UserId.getRoles().toString().contains("ROLE_USER")) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse(date);
		List<Integer> hour= new ArrayList<Integer>();
		hour.add(8);hour.add(9); hour.add(10);hour.add(11);hour.add(12);hour.add(13);hour.add(14);hour.add(15);hour.add(16);
		
		for (Appointment app : appointments.FindDateAppointmentAgent(d,agent.getId().intValue())) {
			System.out.println("---"+Integer.parseInt(app.getBeginhour()));
			if( hour.contains(Integer.parseInt(app.getBeginhour()) ))
			{
				System.out.println("-------");
				hour.remove(hour.indexOf(Integer.parseInt(app.getBeginhour())) );
		}
}
		return ( ("  "+ hour));
		}
		else
		{
			return ("user n'est pas client");
			}
		}
		
					
	@Override
	public String delete_appointment(int user_id,int id_appointment) {
//		User UserId = users.findById(user_id);
//	Appointment appointment = appointments.findById(id_appointment).orElse(null);
//	if(appointment == null) {return ("appointment n'existe pas");}
//		if (UserId.getRoles().toString().contains("ROLE_USER") ) {
//	        if (UserId.getId()==appointment.getUser().getId()){
		
			appointments.deleteById(id_appointment);
			return ("appointment est supprimé");	
//		}
//		
//	else
//		{
//			return  ("Supprision non autorisée");
//		}}
//		
//	else
//		{
//			return  ("user n'est pas client");
//        }
//		
	}
	

			 
//	@Override
//	public String update_appointment_By_agent(int agent_id , int appointment_id,Appointment appointment) {
//		User agent = users.findById(agent_id);
//		
//		 Appointment Update =appointments.findById(appointment_id).orElse(null);
//	
//		if  ( Update == null) {
//			return ("appointment n'existe pas");
//		
//		}
//	
//	 if (agent.getId()==Update.getAgent().getId()){
//	 if ( Update != null) {
//		 Update.setBeginhour(appointment.getBeginhour());
//		 Update.setDate(appointment.getDate());
//		 Update.setDescription(appointment.getDescription());
//			Update.setEndhour(Integer.toString(Integer.parseInt(appointment.getBeginhour()) +1));
//			Update.setStatus(1);
//			
//			
//			appointments.save(Update);
//			
//			return ("appointment est bien modifiée ");
//		} }
//	 
//	 else
//		{
//			return  ("Modification non autorisée");
//		}
//		return null;
//		 
//}
//	
	
	public List<Appointment> getallappointment_status_1(int id_AGENT) {
		User agent = users.findById(id_AGENT);
		
		
		List<Appointment> appointment = new ArrayList<>();
		List<Appointment> appointments2 = (List<Appointment>) appointments.findAll();
		
		
		for (Appointment app : appointments2) {
			if(agent.getId()==app.getAgent().getId())
			{
				appointment.add(app);
			}
		}
		return appointment ;
		}
	public String accepte_appointment(int id_appointment ,int agent_id  ) {
		
//		User agent = users.findById(agent_id);
		Appointment appointment = appointments.findById(id_appointment).orElse(null);
		if  ( appointment == null) {
			return ("appointment n'existe pas");
		
		}
//		 if (agent.getId()==appointment.getAgent().getId()){
//			if(appointment.getStatus() == 0)
//			{
				appointment.setStatus(1);
				appointments.save(appointment);
				return ("appointment est  accépté");

//			}
//			else
//			{
//				return ("appointment est  déja vaildé");
//
//			}
//		 } else {return (" tu n' as pas le droit  d'Accepter des rendez-vous pour d'autres personnes  ");}
}

	public String refut_appointment(int agent_id,int id_appointment) {
		User agent = users.findById(agent_id);
		
		
		Appointment appointment = appointments.findById(id_appointment).orElse(null);
		if  ( appointment == null) {
			return ("appointment n'existe pas");
		
		}
		 if (agent.getId()==appointment.getAgent().getId()){
			 if(appointment.getStatus() == 0)
				{
				appointments.deleteById(appointment.getId());
				return ("appointment est réfusé");

			}
			 else{
					return ("appointment est accepté deja");

				}
		 }

		  else {return (" tu n' as pas le droit  de refuser des rendez-vous pour d'autres personnes  ");}
	}
	
	public List<Appointment> searchappointment(int user_id,String search) {
		
		
	return 	appointments.SearchApp(search);

			

		}

	
	@Override
	public List<Appointment> FindDateAppointmentAgent(java.sql.Date date, int agent_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> SearchApp(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> findClientAppointment(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String lister_date_disponible_byAgent(int id_user, int id_agent, String date) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String ajouter_Agent_rendezVous(int id_user, int id_agent, Appointment appointment) {
	
		User agentId = users.findById(4);
		User UserId = users.findById(id_user);
		
		appointment.setAgent(agentId);
		appointment.setUser(UserId);
		// TODO Auto-generated method stub
		appointments.save(appointment);
		
		
		return null;
	}
		@Override
		public List<Appointment> FindAppointmentsByUser(Long client_id) {
			// TODO Auto-generated method stub
			List<Appointment>  liste =appointments.FindAppointmentsByClient(client_id);
			return liste;
		}


@Override
public String updateappointmentByUser(int user_id, Appointment appointment) {
	// TODO Auto-generated method stub
	 Appointment Update =appointments.findById(appointment.getId()).orElse(null);

		
	 Update.setBeginhour(appointment.getBeginhour());
	 Update.setDate(appointment.getDate());
	 Update.setDescription(appointment.getDescription());
	 Update.setEndhour(Integer.toString(Integer.parseInt(appointment.getBeginhour()) +1));
	 Update.setAppointmentType(appointment.getAppointmentType());
	 Update.setStatus(0);

	
	appointments.save(Update);
	return ("appointment est bien modifiée ");
}
}
