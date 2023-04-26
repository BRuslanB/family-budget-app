package kz.bars.familybudget.service;

import kz.bars.familybudget.model.ExpenseCategory;


import java.util.List;

public interface ExpenseCategoryService {

    List<ExpenseCategory> getAllExpenseCategory();
    ExpenseCategory getExpenseCategory(Long id);
    ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory);
    ExpenseCategory updateExpenseCategory(ExpenseCategory expenseCategory);
    void deleteExpenseCategory(Long id);

}
