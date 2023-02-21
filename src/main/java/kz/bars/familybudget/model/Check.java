package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "checks")
public class Check extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "type_receipt_id")
    private TypeReceipt typeReceipt;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private LocalDate date;

    private String note;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Budget budget;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Purchases purchase;

}
