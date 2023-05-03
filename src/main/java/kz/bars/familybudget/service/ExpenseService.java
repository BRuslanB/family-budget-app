package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.ExpenseDto;
import kz.bars.familybudget.model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpense();
    Expense getExpense(Long id);
    Expense addExpense(Expense expense);
    Expense updateExpense(Expense expense);
    void deleteExpense(Long id);
    ExpenseDto toDto(Expense expense);
    List<ExpenseDto> getAllExpenseDto();
    List<ExpenseDto> getAllExpenseDtoBetweenDate(LocalDate dateFrom, LocalDate dateTo);

}
