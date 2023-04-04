package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role, BigInteger> {

    Role findByRole(String role);
    
}
