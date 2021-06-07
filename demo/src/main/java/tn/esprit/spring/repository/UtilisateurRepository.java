package tn.esprit.spring.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;


@Repository

public interface UtilisateurRepository extends CrudRepository<User, Integer>  
{


	public Optional<User> findById(int id);

	


}  