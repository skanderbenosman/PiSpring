package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Bank;
import tn.esprit.spring.entity.Loan;
import tn.esprit.spring.entity.Purchase;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>{

	 @Query(value="SELECT * from Loan e where e.loan_amount like %:word%",nativeQuery =true)	
	   public List<Loan> searchLoan(@Param("word") String word);

		@Modifying
		@Transactional
		@Query
	    ("UPDATE Loan e SET e.LoanAmount=:LoanAmount,e.LoanTerm=:LoanTerm,e.MonthlyIncome=:MonthlyIncome,e.bank=:bank where e.id=:id")
		public void updateL
		(@Param("LoanAmount")int LoanAmount,@Param("LoanTerm")int LoanTerm,@Param("MonthlyIncome")int MonthlyIncome,@Param("bank")Bank bank,
		@Param("id")Long id);


}
