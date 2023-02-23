package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "type_income")
public class TypeIncome extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

}
