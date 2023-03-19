package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService {

    List<Budget> getAllBudget();
    Budget getBudget(Long id);
    Budget addBudget(Budget budget);
    Budget updateBudget(Budget budget);
    void deleteBudget(Long id);
    BudgetDto toDto(Budget budget);
    List<BudgetDto> getAllBudgetDto();
    List<BudgetDto> getAllBudgetBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
