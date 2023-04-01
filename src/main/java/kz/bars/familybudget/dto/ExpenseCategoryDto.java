package kz.bars.familybudget.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExpenseCategoryDto {

    private BigInteger id;
    private String name;
    private String description;

}
