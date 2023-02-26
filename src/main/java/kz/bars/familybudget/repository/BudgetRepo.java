package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BudgetRepo extends JpaRepository<Budget, Long> {

//    @Query("SELECT SUM(b.value) FROM Budget c JOIN c.checks b WHERE c.typeIncome = :typeIncome_id")
//    Double findBudgetByChecksSumValue(Long typeIncome_id);

}