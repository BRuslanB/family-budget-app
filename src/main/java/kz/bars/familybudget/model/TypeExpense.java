package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class TypeExpense extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    @Column(nullable = false)
    private String name;

    private String description;

    private Boolean isValid = true;

}
