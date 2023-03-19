package kz.bars.familybudget.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExpenseCategoryDto {

    private Long id;
    private String name;
    private String description;

}
