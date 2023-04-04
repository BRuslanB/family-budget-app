package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private final ExpenseCategoryRepo expenseCategoryRepo;

    @Override
    public List<ExpenseCategory> getAllExpenseCategory() {
        return expenseCategoryRepo.findAll();
    }

    @Override
    public ExpenseCategory getExpenseCategory(BigInteger id) {
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
    public void deleteExpenseCategory(BigInteger id) {
        expenseCategoryRepo.deleteById(id);
    }

}
