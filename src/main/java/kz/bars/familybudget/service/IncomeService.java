package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.IncomeDto;
import kz.bars.familybudget.model.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeService {
    List<Income> getAllIncome();
    Income getIncome(Long id);
    Income addIncome(Income income);
    Income updateIncome(Income income);
    void deleteIncome(Long id);
    IncomeDto toDto(Income income);
    List<IncomeDto> getAllIncomeDto();
    List<IncomeDto> getAllIncomeDtoBetweenDate(LocalDate dateFrom, LocalDate dateTo);

}
