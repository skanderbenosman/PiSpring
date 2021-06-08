package tn.esprit.spring.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;


@Repository

public interface UserRepository extends CrudRepository<User, Long>  
{


	public Optional<User> findById(Long id);

	


}  