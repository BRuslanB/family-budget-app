package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.mapper.BudgetMapper;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.repository.BudgetRepo;
import kz.bars.familybudget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    final BudgetRepo budgetRepo;
    final BudgetMapper budgetMapper;

    @Override
    public List<Budget> getAllBudget() {
        return budgetRepo.findAll();
    }

    @Override
    public Budget getBudget(Long id) {
        return budgetRepo.findById(id).orElseThrow();
    }

    @Override
    public Budget addBudget(Budget budget) {
        return budgetRepo.save(budget);
    }

    @Override
    public Budget updateBudget(Budget budget) {
        return budgetRepo.save(budget);
    }

    @Override
    public void deleteBudget(Long id) {
        budgetRepo.deleteById(id);
    }

    @Override
    public List<BudgetDto> getAllBudgetDto() {
        return budgetMapper.toDtoList(budgetRepo.findAll());
    }

    @Override
    public BudgetDto getBudgetDto(Long id) {
        return budgetMapper.toDto(budgetRepo.findById(id).orElseThrow());
    }

    @Override
    public BudgetDto addBudgetDto(BudgetDto budgetDto) {
        return budgetMapper.toDto(budgetRepo.save(budgetMapper.toEntity(budgetDto)));
    }

    @Override
    public BudgetDto updateBudgetDto(BudgetDto budgetDto) {
        return budgetMapper.toDto(budgetRepo.save(budgetMapper.toEntity(budgetDto)));
    }

}
