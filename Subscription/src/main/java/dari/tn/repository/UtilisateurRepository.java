package dari.tn.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dari.tn.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer>  
{


	public Optional<Utilisateur> findById(Long id);
}