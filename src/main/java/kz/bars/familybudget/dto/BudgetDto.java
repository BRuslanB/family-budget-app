package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class BudgetDto {

    private BigInteger id;
    private String income;
    private String description;
    private Double sumVal;

}
