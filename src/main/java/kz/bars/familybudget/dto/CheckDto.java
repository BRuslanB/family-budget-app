package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CheckDto {

    private Long id;
    private Double value;
    private LocalDate date;
    private String note;
    private BudgetDto budget;
    private PurchaseDto purchase;

}
