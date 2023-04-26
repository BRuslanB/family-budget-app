package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
public class Expense extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToOne //no cascade delete
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @OneToMany(mappedBy = "expense", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //orphanRemoval = false)
    private Set<Check> checks;

}
