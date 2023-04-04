package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
@Transactional
public interface ExpenseCategoryRepo extends JpaRepository<ExpenseCategory, BigInteger> {

}
