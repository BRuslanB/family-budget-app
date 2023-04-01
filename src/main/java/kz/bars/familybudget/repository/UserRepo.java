package kz.bars.familybudget.repository;

import kz.bars.familybudget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, BigInteger> {

    User findByEmail(String email);

}
