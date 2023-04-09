package kz.bars.familybudget.controller;

import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeController {
    private final AccountService accountService;
    private final ExpenseCategoryService expenseCategoryService;
    private final PurchaseService purchaseService;
    private final BudgetService budgetService;

    @GetMapping(value = "/")
    public String index() {
        log.info("!Call method /index()");
        return "index";
    }

    @GetMapping(value = "/signin")
    public String signin() {
        log.info("!Call method /signin()");
        return "signin";
    }

    @GetMapping(value = "/authorize")
    public String authorize() {
        log.info("!Call method /authorize()");
        return "authorize";
    }

    @GetMapping(value = "/register")
    public String register() {
        log.info("!Call method /register()");
        return "register";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile() {
        log.info("!Call method /profile()");
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/payments")
    public String payments() {
        log.info("!Call method /payments()");
        return "payments";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/settings")
    public String settings(Model model) {

        List<ExpenseCategory> allExpenseCategory = expenseCategoryService.getAllExpenseCategory();
        model.addAttribute("allExpenseCategory", allExpenseCategory);

        List<Purchase> allPurchase = purchaseService.getAllPurchase();
        model.addAttribute("allExpense", allPurchase);

        List<Budget> allBudget =  budgetService.getAllBudget();
        model.addAttribute("allIncome", allBudget);

        log.info("!Call method /settings()");
        return "settings";
    }

    @GetMapping(value = "/forbidden")
    public String forbidden() {
        log.info("!Call method /forbidden()");
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
            log.info("!User successfully registered, " + "email=" + userEmail + ", first name=" + userFirstname +
                     ", last name=" + userLastname + ", birth day=" + userBirthDay + ", password=" + userPassword +
                     ", repeat password=" + userRePassword);
            return "redirect:/register?success";
        } else {
            log.info("!User not registered, " + "email=" + userEmail + ", first name=" + userFirstname +
                    ", last name=" + userLastname + ", birth day=" + userBirthDay + ", password=" + userPassword +
                    ", repeat password=" + userRePassword);
            return "redirect:/register?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/update-password")
    public String register(@RequestParam(name = "user_old_password") String userOldPassword,
                           @RequestParam(name = "user_new_password") String userNewPassword,
                           @RequestParam(name = "user_re_new_password") String userReNewPassword) {

        if (accountService.updatePassword(userOldPassword, userNewPassword, userReNewPassword) != null) {
            log.info("!User updated the Password successfully, " + "old password=" +
                     userOldPassword.replace("(?)","*") + ", new password=" +
                     userNewPassword.replace("(?)","*") + ", renew password=" +
                     userReNewPassword.replace("(?)","*"));
            return "redirect:/profile?password_success";
        } else {
            log.info("!User has not updated the Password, " + "old password=" +
                    userOldPassword.replace("(?)","*") + ", new password=" +
                    userNewPassword.replace("(?)","*") + ", renew password=" +
                    userReNewPassword.replace("(?)","*"));
            return "redirect:/profile?password_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam(name = "user_firstname") String firstName,
                                @RequestParam(name = "user_lastname") String lastName,
                                @RequestParam(name = "user_birth_day") LocalDate birthDay) {

        if (accountService.updateProfile(firstName, lastName, birthDay) != null) {
            log.info("!User updated the Profile successfully, " + "first name=" + firstName +
                     ", last name=" + lastName + ", birth day=" + birthDay);
            return "redirect:/profile?update_success";
        }
        log.info("!User has not updated the Profile, " + "first name=" + firstName +
                 ", last name=" + lastName + ", birth day=" + birthDay);
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

        log.info("!New Category added, " + "name=" + expenseCategoryName +
                 ", description=" + expenseCategoryDescription);
        return "redirect:/settings?category_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-category-expense")
    public String updateExpenseCategory(@RequestParam(name = "expense_category_id") BigInteger expenseCategoryId,
                                        @RequestParam(name = "expense_category_name") String expenseCategoryName,
                                        @RequestParam(name = "expense_category_description") String expenseCategoryDescription) {

        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

        if (expenseCategory != null) {
            expenseCategory.setName(expenseCategoryName);
            expenseCategory.setDescription(expenseCategoryDescription);
            expenseCategoryService.updateExpenseCategory(expenseCategory);

            log.info("!Category updated successfully, " + "id=" + expenseCategoryId +
                     ", name=" + expenseCategoryName + ", description=" + expenseCategoryDescription);
            return "redirect:/settings?category_success";
        }
        log.info("!Category not updated, " + "id=" + expenseCategoryId +
                 ", name=" + expenseCategoryName + ", description=" + expenseCategoryDescription);
        return "redirect:/settings?category_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-category-expense")
    public String deleteExpenseCategory(@RequestParam(name = "expense_category_id") BigInteger expenseCategoryId) {

        try {
            expenseCategoryService.deleteExpenseCategory(expenseCategoryId);
            log.info("!Category removed, " + "id=" + expenseCategoryId);
            return "redirect:/settings?category_success";

        } catch (Exception e) {
            log.info("!Category not removed, " + "id=" + expenseCategoryId);
            return "redirect:/settings?category_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-expense")
    public String addTypeExpense(@RequestParam(name = "expense_name") String expenseName,
                                 @RequestParam(name = "expense_description") String expenseDescription,
                                 @RequestParam(name = "expense_category_id") BigInteger expenseCategoryId) {

        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

        if (expenseCategory != null) {
            Purchase purchase = new Purchase();
            purchase.setExpense(expenseName);
            purchase.setDescription(expenseDescription);
            purchase.setCategory(expenseCategory);
            purchaseService.addPurchase(purchase);
        }

        log.info("!New Expense added, " + "name=" + expenseName + ", description=" + expenseDescription +
                 ", category=" + expenseCategoryId);
        return "redirect:/settings?expense_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-expense")
    public String updateTypeExpense(@RequestParam(name = "expense_id") BigInteger expenseId,
                                    @RequestParam(name = "expense_name") String expenseName,
                                    @RequestParam(name = "expense_description") String expenseDescription,
                                    @RequestParam(name = "expense_category_id") BigInteger expenseCategoryId) {

        Purchase purchase = purchaseService.getPurchase(expenseId);

        if (purchase != null) {

            ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

            if (expenseCategory != null) {
                purchase.setExpense(expenseName);
                purchase.setDescription(expenseDescription);
                purchase.setCategory(expenseCategory);
                purchaseService.updatePurchase(purchase);
            }

            log.info("!Expense updated successfully, " + "id=" + expenseId + ", name=" + expenseName +
                    ", description=" + expenseDescription + ", category=" + expenseCategoryId);
            return "redirect:/settings?expense_success";
        }

        log.info("!Expense not updated, " + "id=" + expenseId + ", name=" + expenseName +
                ", description=" + expenseDescription + ", category=" + expenseCategoryId);
        return "redirect:/settings?expense_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-expense")
    public String deleteTypeExpense(@RequestParam(name = "expense_id") BigInteger expenseId) {

        try {
            purchaseService.deletePurchase(expenseId);
            log.info("!Expense removed, " + "id=" + expenseId);
            return "redirect:/settings?expense_success";

        } catch (Exception e) {
            log.info("!Expense not removed, " + "id=" + expenseId);
            return "redirect:/settings?expense_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-income")
    public String addTypeIncome(@RequestParam(name = "income_name") String incomeName,
                                @RequestParam(name = "income_description") String incomeDescription) {

        Budget budget = new Budget();
        budget.setIncome(incomeName);
        budget.setDescription(incomeDescription);
        budgetService.addBudget(budget);

        log.info("!New Income added, " + "name=" + incomeName + ", description=" + incomeDescription);
        return "redirect:/settings?income_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-income")
    public String updateTypeIncome(@RequestParam(name = "income_id") BigInteger incomeId,
                                   @RequestParam(name = "income_name") String incomeName,
                                   @RequestParam(name = "income_description") String incomeDescription) {

        Budget budget = budgetService.getBudget(incomeId);

        if (budget != null) {
            budget.setIncome(incomeName);
            budget.setDescription(incomeDescription);
            budgetService.updateBudget(budget);

            log.info("!Income updated successfully, " + "id=" + incomeId + ", name=" + incomeName +
                     ", description=" + incomeDescription);
            return "redirect:/settings?income_success";
        }

        log.info("!Income not updated, " + "id=" + incomeId + ", name=" + incomeName +
                ", description=" + incomeDescription);
        return "redirect:/settings?income_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-income")
    public String deleteTypeIncome(@RequestParam(name = "income_id") BigInteger incomeId) {

        try {
            budgetService.deleteBudget(incomeId);
            log.info("!Income removed, " + "id=" + incomeId);
            return "redirect:/settings?income_success";

        } catch (Exception e) {
            log.info("!Income not removed, " + "id=" + incomeId);
            return "redirect:/settings?income_error";
        }
    }

}
