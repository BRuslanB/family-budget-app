package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.TypeExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TypeExpenseRepo extends JpaRepository<TypeExpense, Long> {

}
