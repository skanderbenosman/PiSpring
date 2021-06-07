package dari.tn.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dari.tn.model.Utilisateur;

@Repository

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer>  
{


	public Optional<Utilisateur> findById(int id);

	


}  