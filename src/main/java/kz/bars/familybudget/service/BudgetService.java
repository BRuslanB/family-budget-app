package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService {

    List<Budget> getAllBudget();
    Budget getPurchase(Long id);
    Budget addPurchase(Budget budget);
    Budget updatePurchase(Budget budget);
    void deletePurchase(Long id);
    BudgetDto toDto(Budget budget);
    List<BudgetDto> getAllBudgetDto();
    List<BudgetDto> getAllBudgetBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
