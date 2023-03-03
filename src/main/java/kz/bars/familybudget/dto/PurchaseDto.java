package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {

    private Long id;
    private TypeExpenseDto typeExpense;
    private Double sumValue;

}
