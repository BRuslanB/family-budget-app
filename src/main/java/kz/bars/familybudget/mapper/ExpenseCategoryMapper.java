package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.ExpenseCategoryDto;
import kz.bars.familybudget.model.ExpenseCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {

    ExpenseCategoryDto toDto(ExpenseCategory expenseCategory);
    ExpenseCategory toEntity(ExpenseCategoryDto expenseCategoryDto);
    List<ExpenseCategoryDto> toDtoList(List<ExpenseCategory> expensCategories);

}
