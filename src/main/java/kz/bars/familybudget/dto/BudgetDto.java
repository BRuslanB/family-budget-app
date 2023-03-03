package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetDto {

    private Long id;
    private TypeIncomeDto typeIncome;
    private Double sumValue;

}
