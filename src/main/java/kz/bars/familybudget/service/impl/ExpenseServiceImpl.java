package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.ExpenseDto;
import kz.bars.familybudget.mapper.ExpenseCategoryMapper;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.Expense;
import kz.bars.familybudget.repository.ExpenseRepo;
import kz.bars.familybudget.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepo.findAll();
    }

    @Override
    public Expense getExpense(Long id) {
        return expenseRepo.findById(id).orElseThrow();
    }

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }

    @Override
    public ExpenseDto toDto(Expense expense) {
        if (expense == null) {
            return null;
        }
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setId(expense.getId());
        expenseDto.setName(expense.getName());
        expenseDto.setDescription(expense.getDescription());
        expenseDto.setCategory(expenseCategoryMapper.toDto(expense.getCategory()));

        return expenseDto;
    }

    @Override
    public List<ExpenseDto> getAllExpenseDto() {
        List<Expense> expenseList;
        expenseList = expenseRepo.findAll();

        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        ExpenseDto expenseDto;
        for (Expense expense : expenseList) {
            expenseDto = new ExpenseDto();
            expenseDto.setId(expense.getId());
            expenseDto.setName(expense.getName());
            expenseDto.setDescription(expense.getDescription());
            expenseDto.setCategory(expenseCategoryMapper.toDto(expense.getCategory()));
            // Count Value
            var sum = 0.0;
            for (Check check : expense.getChecks()) {
                sum += check.getVal();
            }
            expenseDto.setSumVal(Math.round(sum * 100.0) / 100.0);
            //Add to purchaseDtoList
            expenseDtoList.add(expenseDto);
        }
        return expenseDtoList;
    }

    @Override
    public List<ExpenseDto> getAllExpenseBetweenDateDto(LocalDate dateFrom, LocalDate dateTo) {
        List<Expense> expenseList;
        expenseList = expenseRepo.findAllByChecksBetweenDateOrderByDate(dateFrom, dateTo);

        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        ExpenseDto expenseDto;
        for (Expense expense : expenseList) {
            expenseDto = new ExpenseDto();
            expenseDto.setId(expense.getId());
            expenseDto.setName(expense.getName());
            expenseDto.setDescription(expense.getDescription());
            expenseDto.setCategory(expenseCategoryMapper.toDto(expense.getCategory()));
            // Count Value
            var sum = 0.0;
            for (Check check : expense.getChecks()) {
                if (check.getDate().compareTo(dateFrom) >= 0 && check.getDate().compareTo(dateTo) <= 0) {
                    sum += check.getVal();
                }
            }
            expenseDto.setSumVal(Math.round(sum * 100.0) / 100.0);
            //Add to purchaseDtoList
            expenseDtoList.add(expenseDto);
        }
        return expenseDtoList;
    }

}
