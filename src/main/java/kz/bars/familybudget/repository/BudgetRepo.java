package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BudgetRepo extends JpaRepository<Budget, Long> {

}
