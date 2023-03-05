package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CheckRepo extends JpaRepository<Check, Long> {

    List<Check> findAllByDateBetweenOrderByDate(LocalDate date1, LocalDate date2);
    List<Check> findAllByBudgetIdOrderByDate(Long id);
    List<Check> findAllByBudgetIdAndDateBetweenOrderByDate(Long id, LocalDate date1, LocalDate date2);
    List<Check> findAllByPurchaseIdOrderByDate(Long id);
    List<Check> findAllByPurchaseIdAndDateBetweenOrderByDate(Long id, LocalDate date1, LocalDate date2);

}
