package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.TypeIncomeDto;
import kz.bars.familybudget.model.TypeIncome;

import java.util.List;

public interface TypeIncomeService {

    public List<TypeIncome> getAllTypeIncome();
    public TypeIncome getTypeIncome(Long id);
    public TypeIncome addTypeIncome(TypeIncome typeIncome);
    public TypeIncome updateTypeIncome(TypeIncome typeIncome);
    public void deleteTypeIncome(Long id);
    public List<TypeIncomeDto> getAllTypeIncomeDto();
    public TypeIncomeDto getTypeIncomeDto(Long id);
    public TypeIncomeDto addTypeIncomeDto(TypeIncomeDto typeIncomeDto);
    public TypeIncomeDto updateTypeIncomeDto(TypeIncomeDto typeIncomeDto);

}
