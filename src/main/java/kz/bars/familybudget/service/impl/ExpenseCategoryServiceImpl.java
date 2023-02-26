package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.ExpenseCategoryDto;
import kz.bars.familybudget.mapper.ExpenseCategoryMapper;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private final ExpenseCategoryRepo expenseCategoryRepo;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Override
    public List<ExpenseCategory> getAllExpenseCategory() {
        return expenseCategoryRepo.findAll();
    }

    @Override
    public ExpenseCategory getExpenseCategory(Long id) {
        return expenseCategoryRepo.findById(id).orElseThrow();
    }

    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepo.save(expenseCategory);
    }

    @Override
    public ExpenseCategory updateExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepo.save(expenseCategory);
    }

    @Override
    public void deleteExpenseCategory(Long id) {
        expenseCategoryRepo.deleteById(id);
    }

    @Override
    public List<ExpenseCategoryDto> getAllExpenseCategoryDto() {
        return expenseCategoryMapper.toDtoList(expenseCategoryRepo.findAll());
    }

    @Override
    public ExpenseCategoryDto getExpenseCategoryDto(Long id) {
        return expenseCategoryMapper.toDto(expenseCategoryRepo.findById(id).orElseThrow());
    }

    @Override
    public ExpenseCategoryDto addExpenseCategoryDto(ExpenseCategoryDto expenseCategoryDto) {
        return expenseCategoryMapper.toDto(expenseCategoryRepo.save(expenseCategoryMapper.toEntity(expenseCategoryDto)));
    }

    @Override
    public ExpenseCategoryDto updateExpenseCategoryDto(ExpenseCategoryDto expenseCategoryDto) {
        return expenseCategoryMapper.toDto(expenseCategoryRepo.save(expenseCategoryMapper.toEntity(expenseCategoryDto)));
    }
}
