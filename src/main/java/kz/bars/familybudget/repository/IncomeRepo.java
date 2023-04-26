package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface IncomeRepo extends JpaRepository<Income, Long> {

    @Query("SELECT b FROM Income b JOIN b.checks c WHERE c.date >= :date1 AND c.date <= :date2 ORDER BY c.date")
    List<Income> findAllByChecksBetweenDateOrderByDate(LocalDate date1, LocalDate date2);

}