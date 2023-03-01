package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.model.Check;

import java.util.List;

public interface CheckService {
    public CheckDto getCheckDto(Long id);
    public CheckDto addCheckDto(CheckDto checkDto);
    public CheckDto updateCheckDto(CheckDto checkDto);
    public void deleteCheckDto(Long id);
    public List<CheckDto> getAllCheckDto();
    public List<CheckDto> getAllCheckByBudgetIdDto(Long id);
    public List<CheckDto> getAllCheckByPurchaseIdDto(Long id);

}
