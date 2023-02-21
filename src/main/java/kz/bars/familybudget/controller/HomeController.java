package kz.bars.familybudget.controller;

import kz.bars.familybudget.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/signin")
    public String signin(Model model) {
        return "signin";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        return "register";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile(Model model) {
        return "profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String adminPanel(Model model) {
        return "admin";
    }

    @GetMapping(value = "/forbidden")
    public String forbidden(Model model) {
        return "forbidden";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "user_email") String userEmail,
                           @RequestParam(name = "user_firstname") String userFirstname,
                           @RequestParam(name = "user_lastname") String userLastname,
                           @RequestParam(name = "user_birth_day") LocalDate userBirthDay,
                           @RequestParam(name = "user_password") String userPassword,
                           @RequestParam(name = "user_re_password") String userRePassword) {

        if (accountService.registerUser(userEmail, userFirstname, userLastname, userBirthDay,
                userPassword, userRePassword) != null) {
            return "redirect:/register?success";
        } else {
            return "redirect:/register?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/update-password")
    public String register(@RequestParam(name = "user_old_password") String userOldPassword,
                           @RequestParam(name = "user_new_password") String userNewPassword,
                           @RequestParam(name = "user_re_new_password") String userReNewPassword) {

        if (accountService.updatePassword(userOldPassword, userNewPassword, userReNewPassword) != null) {
            return "redirect:/profile?password_success";
        } else {
            return "redirect:/profile?password_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam(name = "user_firstname") String firstName,
                                @RequestParam(name = "user_lastname") String lastName,
                                @RequestParam(name = "user_birth_day") LocalDate birthDay) {

        if (accountService.updateProfile(firstName, lastName, birthDay) != null) {
            return "redirect:/profile?update_success";
        }
        return "redirect:/profile?update_error";
    }

}
