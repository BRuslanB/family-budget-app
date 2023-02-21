package kz.bars.familybudget.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class BudgetDto {

    private Long id;
    private TypeIncomeDto income;
    private List<CheckDto> checks = new ArrayList<>();

}
