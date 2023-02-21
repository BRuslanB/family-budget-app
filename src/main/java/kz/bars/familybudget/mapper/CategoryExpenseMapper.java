package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.CategoryExpenseDto;
import kz.bars.familybudget.model.CategoryExpense;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryExpenseMapper {

    CategoryExpenseDto toDto(CategoryExpense categoryExpense);
    CategoryExpense toEntity(CategoryExpenseDto categoryExpenseDto);
    List<CategoryExpenseDto> toDtoList(List<CategoryExpense> categoryExpenses);

}
