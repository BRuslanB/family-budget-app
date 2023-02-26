package kz.bars.familybudget.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class TypeExpenseDto {

    private Long id;
    private String name;
    private ExpenseCategoryDto expenseCategory;
    private String description;
    private Boolean isValid;

}
