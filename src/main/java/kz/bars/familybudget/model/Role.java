package kz.bars.familybudget.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(nullable = false)
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
