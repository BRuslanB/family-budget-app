package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.TypeIncomeDto;
import kz.bars.familybudget.model.TypeIncome;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeIncomeMapper {

    TypeIncomeDto toDto(TypeIncome typeIncome);
    TypeIncome toEntity(TypeIncomeDto typeIncomeDto);
    List<TypeIncomeDto> toDtoList(List<TypeIncome> typeIncomes);

}
