package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.mapper.TypeIncomeMapper;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.repository.BudgetRepo;
import kz.bars.familybudget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    final BudgetRepo budgetRepo;
    final TypeIncomeMapper typeIncomeMapper;

    @Override
    public BudgetDto toDto(Budget budget) {

        if (budget == null) {
            return null;
        }

        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setId(budget.getId());
        budgetDto.setTypeIncome(typeIncomeMapper.toDto(budget.getTypeIncome()));

        return budgetDto;
    }

    @Override
    public List<BudgetDto> getAllBudgetDto() {

        List<Budget> budgetList;
        budgetList = budgetRepo.findAll();

        List<BudgetDto> budgetDtoList = new ArrayList<>();
        BudgetDto budgetDto;
        for (Budget budget : budgetList) {
            budgetDto = new BudgetDto();
            budgetDto.setId(budget.getId());
            budgetDto.setTypeIncome(typeIncomeMapper.toDto(budget.getTypeIncome()));
            // Count Value
            var sum = 0.0;
            for (Check check : budget.getChecks()) {
                sum += check.getValue();
            }
            budgetDto.setSumValue(Math.round(sum * 100.0) / 100.0);
            //Add to budgetDtoList
            budgetDtoList.add(budgetDto);
        }
        return budgetDtoList;
    }

}
