package kz.bars.familybudget.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//@Table(name = "type_receipt")
public class TypeReceipt extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

}
