package kz.bars.familybudget.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TypeIncomeDto {

    private Long id;
    private String name;
    private String description;

}
