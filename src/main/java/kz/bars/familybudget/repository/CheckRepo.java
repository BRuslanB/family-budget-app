package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface CheckRepo extends JpaRepository<Check, BigInteger> {

    List<Check> findAllByDateBetweenOrderByDate(LocalDate date1, LocalDate date2);
    List<Check> findAllByBudgetIdOrderByDate(BigInteger id);
    List<Check> findAllByBudgetIdAndDateBetweenOrderByDate(BigInteger id, LocalDate date1, LocalDate date2);
    List<Check> findAllByPurchaseIdOrderByDate(BigInteger id);
    List<Check> findAllByPurchaseIdAndDateBetweenOrderByDate(BigInteger id, LocalDate date1, LocalDate date2);

}
