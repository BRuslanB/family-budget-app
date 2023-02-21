package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

}
