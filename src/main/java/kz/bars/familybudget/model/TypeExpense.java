package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
//@Table(name = "type_expense")
public class TypeExpense extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "category_expense_id")
    private CategoryExpense categoryExpense;

    @Column(nullable = false)
    private String name;

    private String description;

}
