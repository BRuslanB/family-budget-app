package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.CheckDto;

import java.time.LocalDate;
import java.util.List;

public interface CheckService {
    CheckDto getCheckDto(Long id);
    CheckDto addCheckDto(CheckDto checkDto);
    CheckDto updateCheckDto(CheckDto checkDto);
    void deleteCheckDto(Long id);
    List<CheckDto> getAllCheckDto();
    List<CheckDto> getAllCheckDtoBetweenDate(LocalDate dateFrom, LocalDate dateTo);
    List<CheckDto> getAllCheckDtoByIncomeId(Long id);
    List<CheckDto> getAllCheckDtoByIncomeBetweenDate(Long id, LocalDate dateFrom, LocalDate dateTo);
    List<CheckDto> getAllCheckDtoByExpenseId(Long id);
    List<CheckDto> getAllCheckDtoByExpenseBetweenDate(Long id, LocalDate dateFrom, LocalDate dateTo);

}
