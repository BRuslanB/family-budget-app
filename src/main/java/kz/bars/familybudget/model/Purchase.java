package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
public class Purchase extends BaseEntity {

    @Column(nullable = false)
    private String expense;

    private String description;

    @OneToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Check> checks;

}
