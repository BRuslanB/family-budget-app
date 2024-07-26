package kz.bars.familybudget.controller;

import kz.bars.familybudget.model.Expense;
import kz.bars.familybudget.model.Income;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeController {
    private final AccountService accountService;
    private final ExpenseCategoryService expenseCategoryService;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @GetMapping(value = "/")
    public String index() {
        log.debug("!Call method /index()");
        return "index";
    }

    @GetMapping(value = "/signin")
    public String signin() {
        log.debug("!Call method /signin()");
        return "signin";
    }

    @GetMapping(value = "/register")
    public String register() {
        log.debug("!Call method /register()");
        return "register";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile() {
        log.debug("!Call method /profile()");
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/payments")
    public String payments() {
        log.debug("!Call method /payments()");
        return "payments";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/settings")
    public String settings(Model model) {

        List<ExpenseCategory> allExpenseCategory = expenseCategoryService.getAllExpenseCategory();
        model.addAttribute("allExpenseCategory", allExpenseCategory);

        List<Expense> allExpense = expenseService.getAllExpense();
        model.addAttribute("allExpense", allExpense);

        List<Income> allIncome =  incomeService.getAllIncome();
        model.addAttribute("allIncome", allIncome);

        log.debug("!Call method /settings()");

        System.out.println("same debug!!!);
        return "settings";
    }

    @GetMapping(value = "/forbidden")
    public String forbidden() {
        log.debug("!Call method /forbidden()");
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
            log.debug("!User successfully registered, email={}, first name={}, last name={}, birth day={}, " +
                             "password={}, repeat password={}", userEmail, userFirstname, userLastname, userBirthDay,
                             userPassword.replaceAll(".", "*"),
                             userRePassword.replaceAll(".", "*"));
            return "redirect:/register?success";
        } else {
            log.debug("!User not registered, email={}, first name={}, last name={}, birth day={}, " +
                    "password={}, repeat password={}",userEmail, userFirstname, userLastname, userBirthDay,
                    userPassword.replaceAll(".", "*"),
                    userRePassword.replaceAll(".", "*"));
            return "redirect:/register?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/update-password")
    public String register(@RequestParam(name = "user_old_password") String userOldPassword,
                           @RequestParam(name = "user_new_password") String userNewPassword,
                           @RequestParam(name = "user_re_new_password") String userReNewPassword) {

        if (accountService.updatePassword(userOldPassword, userNewPassword, userReNewPassword) != null) {
            log.debug("!User updated the Password successfully, old password={}, new password={}, renew password={}",
                    userOldPassword.replaceAll(".", "*"),
                    userNewPassword.replaceAll(".", "*"),
                    userReNewPassword.replaceAll(".", "*"));
            return "redirect:/profile?password_success";
        } else {
            log.debug("!User has not updated the Password, old password={}, new password={}, renew password={}",
                    userOldPassword.replaceAll(".", "*"),
                    userNewPassword.replaceAll(".", "*"),
                    userReNewPassword.replaceAll(".", "*"));
            return "redirect:/profile?password_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam(name = "user_firstname") String firstName,
                                @RequestParam(name = "user_lastname") String lastName,
                                @RequestParam(name = "user_birth_day") LocalDate birthDay) {

        if (accountService.updateProfile(firstName, lastName, birthDay) != null) {
            log.debug("!User updated the Profile successfully, first name={}, last name={}, birth day={}",
                    firstName, lastName, birthDay);
            return "redirect:/profile?update_success";
        }
        log.debug("!User has not updated the Profile, first name={}, last name={}, birth day={}",
                firstName, lastName, birthDay);
        return "redirect:/profile?update_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-category-expense")
    public String addExpenseCategory(@RequestParam(name = "expense_category_name") String expenseCategoryName,
                                     @RequestParam(name = "expense_category_description") String expenseCategoryDescription) {

        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategoryName);
        expenseCategory.setDescription(expenseCategoryDescription);
        expenseCategoryService.addExpenseCategory(expenseCategory);

        log.debug("!New Category added, name={}, description={}", expenseCategoryName, expenseCategoryDescription);
        return "redirect:/settings?category_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-category-expense")
    public String updateExpenseCategory(@RequestParam(name = "expense_category_id") Long expenseCategoryId,
                                        @RequestParam(name = "expense_category_name") String expenseCategoryName,
                                        @RequestParam(name = "expense_category_description") String expenseCategoryDescription) {

        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

        if (expenseCategory != null) {
            expenseCategory.setName(expenseCategoryName);
            expenseCategory.setDescription(expenseCategoryDescription);
            expenseCategoryService.updateExpenseCategory(expenseCategory);

            log.debug("!Category updated successfully, id={}, name={}, description={}",
                    expenseCategoryId, expenseCategoryName, expenseCategoryDescription);
            return "redirect:/settings?category_success";
        }
        log.debug("!Category not updated, id={}, name={}, description={}",
                expenseCategoryId, expenseCategoryName, expenseCategoryDescription);
        return "redirect:/settings?category_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-category-expense")
    public String deleteExpenseCategory(@RequestParam(name = "expense_category_id") Long expenseCategoryId) {

        try {
            expenseCategoryService.deleteExpenseCategory(expenseCategoryId);
            log.error("!Category removed, id={}", expenseCategoryId);
            return "redirect:/settings?category_success";

        } catch (Exception e) {
            log.error("!Category not removed, id={}", expenseCategoryId);
            return "redirect:/settings?category_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-expense")
    public String addTypeExpense(@RequestParam(name = "expense_name") String expenseName,
                                 @RequestParam(name = "expense_description") String expenseDescription,
                                 @RequestParam(name = "expense_category_id") Long expenseCategoryId) {

        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

        if (expenseCategory != null) {
            Expense expense = new Expense();
            expense.setName(expenseName);
            expense.setDescription(expenseDescription);
            expense.setCategory(expenseCategory);
            expenseService.addExpense(expense);
        }

        log.debug("!New Expense added, name={}, description={}, category={}",
                expenseName, expenseDescription, expenseCategoryId);
        return "redirect:/settings?expense_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-expense")
    public String updateTypeExpense(@RequestParam(name = "expense_id") Long expenseId,
                                    @RequestParam(name = "expense_name") String expenseName,
                                    @RequestParam(name = "expense_description") String expenseDescription,
                                    @RequestParam(name = "expense_category_id") Long expenseCategoryId) {

        Expense expense = expenseService.getExpense(expenseId);

        if (expense != null) {

            ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

            if (expenseCategory != null) {
                expense.setName(expenseName);
                expense.setDescription(expenseDescription);
                expense.setCategory(expenseCategory);
                expenseService.updateExpense(expense);
            }

            log.debug("!Expense updated successfully id={}, name={}, description={}, category={}",
                    expenseId, expenseName, expenseDescription, expenseCategoryId);
            return "redirect:/settings?expense_success";
        }

        log.debug("!Expense not updated, id={}, name={}, description={}, category={}",
                expenseId, expenseName, expenseDescription, expenseCategoryId);
        return "redirect:/settings?expense_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-expense")
    public String deleteTypeExpense(@RequestParam(name = "expense_id") Long expenseId) {

        try {
            expenseService.deleteExpense(expenseId);
            log.error("!Expense removed, id={}", expenseId);
            return "redirect:/settings?expense_success";

        } catch (Exception e) {
            log.error("!Expense not removed, id={}", expenseId);
            return "redirect:/settings?expense_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-income")
    public String addTypeIncome(@RequestParam(name = "income_name") String incomeName,
                                @RequestParam(name = "income_description") String incomeDescription) {

        Income income = new Income();
        income.setName(incomeName);
        income.setDescription(incomeDescription);
        incomeService.addIncome(income);

        log.debug("!New Income added, name={}, description={}",incomeName, incomeDescription);
        return "redirect:/settings?income_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-income")
    public String updateTypeIncome(@RequestParam(name = "income_id") Long incomeId,
                                   @RequestParam(name = "income_name") String incomeName,
                                   @RequestParam(name = "income_description") String incomeDescription) {

        Income income = incomeService.getIncome(incomeId);

        if (income != null) {
            income.setName(incomeName);
            income.setDescription(incomeDescription);
            incomeService.updateIncome(income);

            log.debug("!Income updated successfully, id={}, name={}, description={}", incomeId, incomeName, incomeDescription);
            return "redirect:/settings?income_success";
        }

        log.debug("!Income not updated, id={}, name={}, description={}", incomeId,incomeName, incomeDescription);
        return "redirect:/settings?income_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-income")
    public String deleteTypeIncome(@RequestParam(name = "income_id") Long incomeId) {

        try {
            incomeService.deleteIncome(incomeId);
            log.error("!Income removed, id={}", incomeId);
            return "redirect:/settings?income_success";

        } catch (Exception e) {
            log.error("!Income not removed, id={}", incomeId);
            return "redirect:/settings?income_error";
        }
    }

}
