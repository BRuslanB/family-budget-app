package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseDto {

    private Long id;
    private String name;
    private String description;
    private ExpenseCategoryDto category;
    private Double sumVal;

}
