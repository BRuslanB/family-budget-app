package kz.bars.familybudget.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeDto {

    private Long id;
    private String name;
    private String description;
    private Double sumVal;

}
