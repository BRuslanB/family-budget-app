package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;

import java.util.List;

public interface BudgetService {

    public BudgetDto toDto(Budget budget);
    public List<BudgetDto> getAllBudgetDto();

}
