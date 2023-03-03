package kz.bars.familybudget.controller;

import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.service.*;
import lombok.RequiredArgsConstructor;
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
public class HomeController {
    private final AccountService accountService;
    private final ExpenseCategoryService expenseCategoryService;
    private final PurchaseService purchaseService;
    private final BudgetService budgetService;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/payments")
    public String payments(Model model) {
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

        return "settings";
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-category-expense")
    public String addExpenseCategory(@RequestParam(name = "expense_category_name") String expenseCategoryName,
                                     @RequestParam(name = "expense_category_description") String expenseCategoryDescription) {

        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategoryName);
        expenseCategory.setDescription(expenseCategoryDescription);
        expenseCategoryService.addExpenseCategory(expenseCategory);

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

            return "redirect:/settings?category_success";
        }
        return "redirect:/settings?category_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-category-expense")
    public String deleteExpenseCategory(@RequestParam(name = "expense_category_id") Long expenseCategoryId) {

        try {
            expenseCategoryService.deleteExpenseCategory(expenseCategoryId);
            return "redirect:/settings?category_success";

        } catch (Exception e) {

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
            Purchase purchase = new Purchase();
            purchase.setExpense(expenseName);
            purchase.setDescription(expenseDescription);
            purchase.setCategory(expenseCategory);
            purchaseService.addPurchase(purchase);
        }

        return "redirect:/settings?expense_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-expense")
    public String updateTypeExpense(@RequestParam(name = "expense_id") Long expenseId,
                                    @RequestParam(name = "expense_name") String expenseName,
                                    @RequestParam(name = "expense_description") String expenseDescription,
                                    @RequestParam(name = "expense_category_id") Long expenseCategoryId) {

        Purchase purchase = purchaseService.getPurchase(expenseId);

        if (purchase != null) {

            ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(expenseCategoryId);

            if (expenseCategory != null) {
                purchase.setExpense(expenseName);
                purchase.setDescription(expenseDescription);
                purchase.setCategory(expenseCategory);
                purchaseService.updatePurchase(purchase);
            }

            return "redirect:/settings?expense_success";
        }
        return "redirect:/settings?expense_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-expense")
    public String deleteTypeExpense(@RequestParam(name = "expense_id") Long expenseId) {

        try {
            purchaseService.deletePurchase(expenseId);
            return "redirect:/settings?expense_success";

        } catch (Exception e) {

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
        budgetService.addPurchase(budget);

        return "redirect:/settings?income_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-income")
    public String updateTypeIncome(@RequestParam(name = "income_id") Long incomeId,
                                   @RequestParam(name = "income_name") String incomeName,
                                   @RequestParam(name = "income_description") String incomeDescription) {

        Budget budget = budgetService.getPurchase(incomeId);

        if (budget != null) {

            budget.setIncome(incomeName);
            budget.setDescription(incomeDescription);
            budgetService.updatePurchase(budget);

            return "redirect:/settings?income_success";
        }
        return "redirect:/settings?income_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-income")
    public String deleteTypeIncome(@RequestParam(name = "income_id") Long incomeId) {

        try {
            budgetService.deletePurchase(incomeId);
            return "redirect:/settings?income_success";

        } catch (Exception e) {

            return "redirect:/settings?income_error";
        }
    }

}
