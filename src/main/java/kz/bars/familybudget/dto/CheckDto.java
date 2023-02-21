package kz.bars.familybudget.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CheckDto {

    private Long id;
    private TypeReceiptDto typeReceipt;
    private Double value;
    private LocalDate date;
    private String note;

}
