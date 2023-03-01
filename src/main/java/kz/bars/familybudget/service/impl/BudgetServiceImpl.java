package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.dto.TypeIncomeDto;
import kz.bars.familybudget.mapper.BudgetMapper;
import kz.bars.familybudget.mapper.TypeIncomeMapper;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.TypeIncome;
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
//    final BudgetMapper budgetMapper;
    final TypeIncomeMapper typeIncomeMapper;

//    @Override
//    public List<Budget> getAllBudget() {
//        return budgetRepo.findAll();
//    }
//
//    @Override
//    public Budget getBudget(Long id) {
//        return budgetRepo.findById(id).orElseThrow();
//    }
//
//    @Override
//    public Budget addBudget(Budget budget) {
//        return budgetRepo.save(budget);
//    }
//
//    @Override
//    public Budget updateBudget(Budget budget) {
//        return budgetRepo.save(budget);
//    }
//
//    @Override
//    public void deleteBudget(Long id) {
//        budgetRepo.deleteById(id);
//    }

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
//        return budgetMapper.toDtoList(budgetRepo.findAll());
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

//    @Override
//    public BudgetDto getBudgetDto(Long id) {
//        return budgetMapper.toDto(budgetRepo.findById(id).orElseThrow());
//    }
//
//    @Override
//    public BudgetDto addBudgetDto(BudgetDto budgetDto) {
//        return budgetMapper.toDto(budgetRepo.save(budgetMapper.toEntity(budgetDto)));
//    }
//
//    @Override
//    public BudgetDto updateBudgetDto(BudgetDto budgetDto) {
//        return budgetMapper.toDto(budgetRepo.save(budgetMapper.toEntity(budgetDto)));
//    }

//    @Override
//    public Double budgetByChecksSum(Long id) {
//        return budgetRepo.findBudgetByChecksSumValue(id);
//    }

}
