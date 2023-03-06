package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.CheckDto;

import java.time.LocalDate;
import java.util.List;

public interface CheckService {
    public CheckDto getCheckDto(Long id);
    public CheckDto addCheckDto(CheckDto checkDto);
    public CheckDto updateCheckDto(CheckDto checkDto);
    public void deleteCheckDto(Long id);
    public List<CheckDto> getAllCheckDto();
    public List<CheckDto> getAllCheckBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);
    public List<CheckDto> getAllCheckByBudgetIdDto(Long id);
    public List<CheckDto> getAllCheckByBudgetBetweenDateDto(Long id, LocalDate dateFrom, LocalDate dateTo);
    public List<CheckDto> getAllCheckByPurchaseIdDto(Long id);
    public List<CheckDto> getAllCheckByPurchaseBetweenDateDto(Long id, LocalDate dateFrom, LocalDate dateTo);

}
