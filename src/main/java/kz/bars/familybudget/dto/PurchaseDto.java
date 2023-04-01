package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PurchaseDto {

    private BigInteger id;
    private String expense;
    private String description;
    private ExpenseCategoryDto category;
    private Double sumVal;

}
