package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;

import java.util.List;

public interface BudgetService {

    public List<Budget> getAllBudget();
    public Budget getBudget(Long id);
    public Budget addBudget(Budget budget);
    public Budget updateBudget(Budget budget);
    public void deleteBudget(Long id);
    public List<BudgetDto> getAllBudgetDto();
    public BudgetDto getBudgetDto(Long id);
    public BudgetDto addBudgetDto(BudgetDto budgetDto);
    public BudgetDto updateBudgetDto(BudgetDto budgetDto);

}
