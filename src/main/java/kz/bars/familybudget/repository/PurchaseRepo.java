package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    @Query("SELECT b FROM Purchase b JOIN b.checks c WHERE c.date >= :date1 AND c.date <= :date2 ORDER BY c.date")
    List<Purchase> findAllByChecksBetweenDateOrderByDate(LocalDate date1, LocalDate date2);

}
