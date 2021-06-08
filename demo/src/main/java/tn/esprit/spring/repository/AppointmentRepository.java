package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Appointment;





@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

	@Query("select a  from Appointment a  join a.agent g WHERE  a.date =:date and g.id =:agent_id and status='1'")
	List<Appointment> FindDateAppointmentAgent(@Param("date") java.util.Date date,@Param("agent_id") int agent_id);


	@Query("SELECT m FROM Appointment m WHERE m.Description LIKE %:search%  OR m.date LIKE %:search% OR m.Beginhour LIKE %:search% ")
	List<Appointment> SearchApp(@Param("search") String search);
	
	@Query("select a  from Appointment a  join a.user  u WHERE  u.id =:client_id ")
	List<Appointment> FindAppointmentsByClient(@Param("client_id") Long client_id);

//	ONLINE,FACETOFACE
	@Query("Select count(a) from Appointment a ")
	long nbAppointment();
  @Query("select count(a) from Appointment a  where a.appointmentType Like CONCAT('%',:type,'%')  ")
  long nbAppointmenttypeFACETOFACE(@Param("type") String type);
  @Query("select count(a) from Appointment a  where a.appointmentType Like CONCAT('%',:type,'%')  ")
  long nbAppointmenttypeONLINE(@Param("type") String type);
	
	
	
	
}
