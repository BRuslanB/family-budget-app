package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
public class Purchase extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "type_expense_id", unique = true)
    private TypeExpense typeExpense;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Check> checks;

}
