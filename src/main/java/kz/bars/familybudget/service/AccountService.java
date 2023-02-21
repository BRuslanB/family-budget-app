package kz.bars.familybudget.service;

import kz.bars.familybudget.model.User;

import java.time.LocalDate;

public interface AccountService {

    public User registerUser(String email, String firstName, String lastName, LocalDate birthDay,
                             String password, String rePassword);
    public User updatePassword(String oldPassword, String newPassword, String rePassword);
    public User updateProfile(String firstName, String lastName, LocalDate birthDay);

}
