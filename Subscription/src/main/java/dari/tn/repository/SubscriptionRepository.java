package dari.tn.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dari.tn.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>  
{

    List<Subscription> findSubscriptionByTitle(String Title);


}  
