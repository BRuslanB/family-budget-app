package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.ExpenseCategoryDto;
import kz.bars.familybudget.model.ExpenseCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {

    ExpenseCategoryDto toDto(ExpenseCategory expenseCategory);

}
