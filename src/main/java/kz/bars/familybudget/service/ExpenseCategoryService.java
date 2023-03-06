package kz.bars.familybudget.service;

import kz.bars.familybudget.model.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService {

    public List<ExpenseCategory> getAllExpenseCategory();
    public ExpenseCategory getExpenseCategory(Long id);
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory);
    public ExpenseCategory updateExpenseCategory(ExpenseCategory expenseCategory);
    public void deleteExpenseCategory(Long id);

}
