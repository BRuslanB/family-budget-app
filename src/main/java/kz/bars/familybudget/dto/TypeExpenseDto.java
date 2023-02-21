package kz.bars.familybudget.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class TypeExpenseDto {

    private Long id;
    private String name;
    private CategoryExpenseDto categoryExpense;
    private String description;

}
