package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.TypeExpenseDto;
import kz.bars.familybudget.model.TypeExpense;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeExpenseMapper {

    TypeExpenseDto toDto(TypeExpense typeExpense);
    TypeExpense toEntity(TypeExpenseDto typeExpenseDto);
    List<TypeExpenseDto> toDtoList(List<TypeExpense> typeExpenses);

}
