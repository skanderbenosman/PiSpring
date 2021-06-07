package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Loan;

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

@Query(value="SELECT * from Bank r where r.name_bank like %:word%",nativeQuery =true)	
public List<Bank> searchBank(@Param("word") String word);

}
