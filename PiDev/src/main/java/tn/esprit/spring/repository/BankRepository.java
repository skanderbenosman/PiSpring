package tn.esprit.spring.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {
	@Modifying
	@Transactional
	@Query
    ("UPDATE Bank e SET e.NameBank=:NameBank where e.id=:id")
	public void updateB
	(@Param("NameBank")String NameBank,
	@Param("id")Long id) ;
	

@Query(value="SELECT * from Bank WHERE NameBank=:NameBank",nativeQuery =true)
	public Bank getIDbank(@Param("NameBank") String NameBank);


}
