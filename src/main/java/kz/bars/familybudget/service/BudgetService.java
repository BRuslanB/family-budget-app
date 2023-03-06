package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService {

    public List<Budget> getAllBudget();
    public Budget getPurchase(Long id);
    public Budget addPurchase(Budget budget);
    public Budget updatePurchase(Budget budget);
    public void deletePurchase(Long id);
    public BudgetDto toDto(Budget budget);
    public List<BudgetDto> getAllBudgetDto();
    public List<BudgetDto> getAllBudgetBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
