package dari.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dari.tn.model.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {





}
