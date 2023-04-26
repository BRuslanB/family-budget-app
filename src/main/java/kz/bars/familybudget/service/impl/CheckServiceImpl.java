package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.model.Income;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.Expense;
import kz.bars.familybudget.repository.IncomeRepo;
import kz.bars.familybudget.repository.CheckRepo;
import kz.bars.familybudget.repository.ExpenseRepo;
import kz.bars.familybudget.service.IncomeService;
import kz.bars.familybudget.service.CheckService;
import kz.bars.familybudget.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final CheckRepo checkRepo;
    private final IncomeRepo incomeRepo;
    private final IncomeService incomeService;
    private final ExpenseRepo expenseRepo;
    private final ExpenseService expenseService;

    @Override
    public CheckDto getCheckDto(Long id) {

        Check check = checkRepo.findById(id).orElse(null);
        CheckDto checkDto = new CheckDto();

        if (check != null) {
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setIncome(incomeService.toDto(check.getIncome()));
            checkDto.setExpense(expenseService.toDto(check.getExpense()));
        }
        return checkDto;
    }

    @Override
    public CheckDto addCheckDto(CheckDto checkDto) {

        Check check = new Check();

        check.setVal(checkDto.getVal());
        check.setDate(checkDto.getDate());
        check.setNote(checkDto.getNote());
        try {
            if (checkDto.getIncome() != null) {
                incomeRepo.findById(checkDto.getIncome().getId()).ifPresent(check::setIncome);
            }
        } catch(Exception ex) {
//            check.setBudget(null);
        }
        try {
            if (checkDto.getExpense() != null) {
                expenseRepo.findById(checkDto.getExpense().getId()).ifPresent(check::setExpense);
            }
        } catch(Exception ex) {
//            check.setPurchase(null);
        }
       checkRepo.save(check);
       return checkDto;
    }

    @Override
    public CheckDto updateCheckDto(CheckDto checkDto) {
        Check check;
        try {
            check = checkRepo.findById(checkDto.getId()).orElseThrow();
        } catch (Exception ex) {
            check = null;
        }

        if (check != null) {
            check.setVal(checkDto.getVal());
            check.setDate(checkDto.getDate());
            check.setNote(checkDto.getNote());
            try {
                Income income = incomeRepo.findById(checkDto.getIncome().getId()).orElseThrow();
                check.setIncome(income);
            } catch (Exception ex) {
//                check.setBudget(null);
            }
            try {
                Expense expense = expenseRepo.findById(checkDto.getExpense().getId()).orElseThrow();
                check.setExpense(expense);
            } catch (Exception ex) {
//                check.setPurchase(null);
            }
            checkRepo.save(check);
        }
        return checkDto;
    }

    @Override
    public void deleteCheckDto(Long id) {
        checkRepo.deleteById(id);
    }

    @Override
    public List<CheckDto> getAllCheckDto() {
        List<Check> checkList;
        checkList = checkRepo.findAll(Sort.by(Sort.Order.by("date")));

        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;
        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setIncome(incomeService.toDto(check.getIncome()));
            checkDto.setExpense(expenseService.toDto(check.getExpense()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckBetweenDateDto(LocalDate dateFrom, LocalDate dateTo) {
        List<Check> checkList;
        checkList = checkRepo.findAllByDateBetweenOrderByDate(dateFrom, dateTo);

        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;
        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setIncome(incomeService.toDto(check.getIncome()));
            checkDto.setExpense(expenseService.toDto(check.getExpense()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByBudgetIdDto(Long id) {

        List<Check> checkList = checkRepo.findAllByIncomeIdOrderByDate(id);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setIncome(incomeService.toDto(check.getIncome()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByBudgetBetweenDateDto(Long id, LocalDate dateFrom, LocalDate dateTo) {

        List<Check> checkList = checkRepo.findAllByIncomeIdAndDateBetweenOrderByDate(id, dateFrom, dateTo);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setIncome(incomeService.toDto(check.getIncome()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByPurchaseIdDto(Long id) {

        List<Check> checkList = checkRepo.findAllByExpenseIdOrderByDate(id);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setExpense(expenseService.toDto(check.getExpense()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByPurchaseBetweenDateDto(Long id, LocalDate dateFrom, LocalDate dateTo) {

        List<Check> checkList = checkRepo.findAllByExpenseIdAndDateBetweenOrderByDate(id, dateFrom, dateTo);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setExpense(expenseService.toDto(check.getExpense()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

}
