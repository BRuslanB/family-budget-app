package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
public class Budget extends BaseEntity {

    @Column(nullable = false)
    private String income;

    private String description;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Check> checks;

}
