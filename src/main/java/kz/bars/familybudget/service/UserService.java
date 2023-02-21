package kz.bars.familybudget.service;

import kz.bars.familybudget.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public UserDetails loadUserByUsername(String username);
    public User getCurrentUser();

}
