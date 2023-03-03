package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetDto {

    private Long id;
    private String income;
    private String description;
    private Double sumValue;

}
