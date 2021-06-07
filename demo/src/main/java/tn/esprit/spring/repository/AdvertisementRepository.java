package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.entity.User;


@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement,Long>{
public Optional<Advertisement> findById(Long id);
public Iterable<Advertisement> findByUser(User u);
@Query(value="SELECT * from advertisement e where e.title like %:word% or e.state like %:word% and e.removed=0",nativeQuery =true)
public List<Advertisement> searchEvent(@Param("word") String word);
@Query(value="SELECT * from advertisement e where e.title like %:word2% and e.removed=0 and e.state='Available'",nativeQuery =true)
public List<Advertisement> searchEvent2(@Param("word2") String word2);

}
