package kz.bars.familybudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "checks")
public class Check extends BaseEntity {

//    @OneToOne
//    @JoinColumn(name = "type_receipt_id")
//    private TypeReceipt typeReceipt;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private LocalDate date;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="budget_id")
    @JsonIgnore
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="purchase_id")
    @JsonIgnore
    private Purchase purchase;

}
