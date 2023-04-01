package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface BudgetService {

    List<Budget> getAllBudget();
    Budget getBudget(BigInteger id);
    Budget addBudget(Budget budget);
    Budget updateBudget(Budget budget);
    void deleteBudget(BigInteger id);
    BudgetDto toDto(Budget budget);
    List<BudgetDto> getAllBudgetDto();
    List<BudgetDto> getAllBudgetBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
