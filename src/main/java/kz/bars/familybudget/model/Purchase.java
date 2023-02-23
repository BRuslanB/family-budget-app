package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Purchase extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "type_expense_id")
    private TypeExpense typeExpense;

    @OneToMany
    private List<Check> checks = new ArrayList<>();

}
