package kz.bars.familybudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "checks")
public class Check extends BaseEntity {

    @Column(nullable = false)
    private Double val;

    @Column(nullable = false)
    private LocalDate date;

    private String note;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="budget_id")
    @JsonIgnore
    private Budget budget;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="purchase_id")
    @JsonIgnore
    private Purchase purchase;

}
