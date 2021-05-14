package dari.tn.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dari.tn.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>  
{


	public Optional<Subscription> findById(int id);



}  
