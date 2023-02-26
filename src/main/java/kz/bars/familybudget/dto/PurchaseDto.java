package kz.bars.familybudget.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Builder
public class PurchaseDto {

    private Long id;
    private TypeExpenseDto typeExpense;
    private Double sumValue;
//    private List<CheckDto> checks = new ArrayList<>();

}
