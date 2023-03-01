package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CheckRepo extends JpaRepository<Check, Long> {

    List<Check> findAllByBudgetId(Long id);
    List<Check> findAllByPurchaseId(Long id);

}
