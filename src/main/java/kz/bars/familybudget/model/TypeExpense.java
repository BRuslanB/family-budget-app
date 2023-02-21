package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@Table(name = "type_expense")
public class TypeExpense extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "category_expense_id")
    private CategoryExpense categoryExpense;

    private String description;

}
