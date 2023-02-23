package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "category_expenses")
public class CategoryExpense extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

}
