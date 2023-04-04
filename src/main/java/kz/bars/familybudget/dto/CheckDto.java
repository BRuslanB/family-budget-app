package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
public class CheckDto {

    private BigInteger id;
    private Double val;
    private LocalDate date;
    private String note;
    private BudgetDto budget;
    private PurchaseDto purchase;

}
