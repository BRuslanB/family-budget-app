package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TypeIncome extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    private Boolean isValid = true;

}
