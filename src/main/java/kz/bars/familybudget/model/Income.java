package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
public class Income extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "income", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //orphanRemoval = false)
    private Set<Check> checks;

}
