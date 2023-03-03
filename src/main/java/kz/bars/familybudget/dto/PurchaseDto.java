package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {

    private Long id;
    private String expense;
    private String description;
    private ExpenseCategoryDto category;
    private Double sumValue;

}
