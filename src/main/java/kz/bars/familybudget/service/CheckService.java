package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.CheckDto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface CheckService {
    CheckDto getCheckDto(BigInteger id);
    CheckDto addCheckDto(CheckDto checkDto);
    CheckDto updateCheckDto(CheckDto checkDto);
    void deleteCheckDto(BigInteger id);
    List<CheckDto> getAllCheckDto();
    List<CheckDto> getAllCheckBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);
    List<CheckDto> getAllCheckByBudgetIdDto(BigInteger id);
    List<CheckDto> getAllCheckByBudgetBetweenDateDto(BigInteger id, LocalDate dateFrom, LocalDate dateTo);
    List<CheckDto> getAllCheckByPurchaseIdDto(BigInteger id);
    List<CheckDto> getAllCheckByPurchaseBetweenDateDto(BigInteger id, LocalDate dateFrom, LocalDate dateTo);

}
