package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface CheckRepo extends JpaRepository<Check, Long> {

    List<Check> findAllByDateBetweenOrderByDate(LocalDate date1, LocalDate date2);
    List<Check> findAllByIncomeIdOrderByDate(Long id);
    List<Check> findAllByIncomeIdAndDateBetweenOrderByDate(Long id, LocalDate date1, LocalDate date2);
    List<Check> findAllByExpenseIdOrderByDate(Long id);
    List<Check> findAllByExpenseIdAndDateBetweenOrderByDate(Long id, LocalDate date1, LocalDate date2);

}
