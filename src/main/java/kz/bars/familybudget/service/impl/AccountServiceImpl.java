package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.Role;
import kz.bars.familybudget.model.User;
import kz.bars.familybudget.repository.RoleRepo;
import kz.bars.familybudget.repository.UserRepo;
import kz.bars.familybudget.service.AccountService;
import kz.bars.familybudget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final UserService userService;

    public User registerUser(String email, String firstName, String lastName, LocalDate birthDay,
                             String password, String rePassword) {

        User checkUser = userRepo.findByEmail(email);

        if (checkUser == null) {
            if (password.equals(rePassword)) {

                List<Role> roles = new ArrayList<>();
                Role userRole = roleRepo.findByRole("ROLE_USER");
                roles.add(userRole);

                User user = User
                        .builder()
                        .email(email)
                        .firstname(firstName)
                        .lastname(lastName)
                        .birthDay(birthDay)
                        .roles(roles)
                        .password(passwordEncoder.encode(password))
                        .build();

                return userRepo.save(user);
            }
        }
        return null;
    }

    public User updatePassword(String oldPassword, String newPassword, String rePassword) {

        User currentUser = userService.getCurrentUser();

        if (currentUser != null) {
            if (newPassword.equals(rePassword) && passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
                currentUser.setPassword(passwordEncoder.encode(newPassword));
                return userRepo.save(currentUser);
            }
        }
        return null;
    }

    public User updateProfile(String firstName, String lastName, LocalDate birthDay) {

        User currentUser = userService.getCurrentUser();

        if (currentUser != null) {
            currentUser.setFirstname(firstName);
            currentUser.setLastname(lastName);
            currentUser.setBirthDay(birthDay);
            return userRepo.save(currentUser);
        }
        return null;
    }

}