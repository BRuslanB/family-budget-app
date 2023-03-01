package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Budget extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "type_income_id", unique = true)
    private TypeIncome typeIncome;

    //    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "budget_id")
    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Check> checks;

}
