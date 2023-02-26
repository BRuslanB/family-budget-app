package kz.bars.familybudget.controller;

import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.model.TypeExpense;
import kz.bars.familybudget.model.TypeIncome;
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
//    private final TypeReceiptService typeReceiptService;
    private final TypeExpenseService typeExpenseService;
    private final TypeIncomeService typeIncomeService;

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

//        List<TypeReceipt> allTypeReceipt = typeReceiptService.getAllTypeReceipt();
//        model.addAttribute("allTypeReceipt", allTypeReceipt);

        List<ExpenseCategory> allExpenseCategory = expenseCategoryService.getAllExpenseCategory();
        model.addAttribute("allExpenseCategory", allExpenseCategory);

        List<TypeExpense> allTypeExpense = typeExpenseService.getAllTypeExpense();
        model.addAttribute("allTypeExpense", allTypeExpense);

        List<TypeIncome> allTypeIncome = typeIncomeService.getAllTypeIncome();
        model.addAttribute("allTypeIncome", allTypeIncome);

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

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/add-type-receipt")
//    public String addTypeReceipt(@RequestParam(name = "type_receipt_name") String typeReceiptName) {
//
//        TypeReceipt typeReceipt = new TypeReceipt();
//        typeReceipt.setName(typeReceiptName);
//        typeReceiptService.addTypeReceipt(typeReceipt);
//
//        return "redirect:/settings?receipt_success";
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/update-type-receipt")
//    public String updateTypeReceipt(@RequestParam(name = "type_receipt_id") Long typeReceiptId,
//                                    @RequestParam(name = "type_receipt_name") String typeReceiptName) {
//
//        TypeReceipt typeReceipt = typeReceiptService.getTypeReceipt(typeReceiptId);
//
//        if (typeReceipt != null) {
//            typeReceipt.setName(typeReceiptName);
//            typeReceiptService.updateTypeReceipt(typeReceipt);
//
//            return "redirect:/settings?receipt_success";
//        }
//        return "redirect:/settings?receipt_error";
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/delete-type-receipt")
//    public String deleteTypeReceipt(@RequestParam(name = "type_receipt_id") Long typeReceiptId){
//
//        try {
//            typeReceiptService.deleteTypeReceipt(typeReceiptId);
//            return "redirect:/settings?receipt_success";
//
//        } catch (Exception e) {
//
//            return "redirect:/settings?receipt_error";
//        }
//    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-category-expense")
    public String addExpenseCategory(@RequestParam(name = "expense_category_name") String ExpenseCategoryName) {

        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(ExpenseCategoryName);
        expenseCategoryService.addExpenseCategory(expenseCategory);

        return "redirect:/settings?category_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-category-expense")
    public String updateExpenseCategory(@RequestParam(name = "expense_category_id") Long ExpenseCategoryId,
                                        @RequestParam(name = "expense_category_name") String ExpenseCategoryName) {

        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(ExpenseCategoryId);

        if (expenseCategory != null) {
            expenseCategory.setName(ExpenseCategoryName);
            expenseCategoryService.updateExpenseCategory(expenseCategory);

            return "redirect:/settings?category_success";
        }
        return "redirect:/settings?category_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-category-expense")
    public String deleteExpenseCategory(@RequestParam(name = "expense_category_id") Long ExpenseCategoryId) {

        try {
            expenseCategoryService.deleteExpenseCategory(ExpenseCategoryId);
            return "redirect:/settings?category_success";

        } catch (Exception e) {

            return "redirect:/settings?category_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-type-expense")
    public String addTypeExpense(@RequestParam(name = "type_expense_name") String typeExpenseName,
                                 @RequestParam(name = "expense_category_id") Long ExpenseCategoryId) {

        ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(ExpenseCategoryId);

        if (expenseCategory != null) {
            TypeExpense typeExpense = new TypeExpense();
            typeExpense.setName(typeExpenseName);
            typeExpense.setExpenseCategory(expenseCategory);
            typeExpenseService.addTypeExpense(typeExpense);
        }

        return "redirect:/settings?expense_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-type-expense")
    public String updateTypeExpense(@RequestParam(name = "type_expense_id") Long typeExpenseId,
                                    @RequestParam(name = "type_expense_name") String typeExpenseName,
                                    @RequestParam(name = "expense_category_id") Long ExpenseCategoryId) {

        TypeExpense typeExpense = typeExpenseService.getTypeExpense(typeExpenseId);

        if (typeExpense != null) {
            ExpenseCategory expenseCategory = expenseCategoryService.getExpenseCategory(ExpenseCategoryId);

            if (expenseCategory != null) {
                typeExpense.setName(typeExpenseName);
                typeExpense.setExpenseCategory(expenseCategory);
                typeExpenseService.updateTypeExpense(typeExpense);
            }

            return "redirect:/settings?expense_success";
        }
        return "redirect:/settings?expense_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-type-expense")
    public String deleteTypeExpense(@RequestParam(name = "type_expense_id") Long typeExpenseId) {

        try {
            typeExpenseService.deleteTypeExpense(typeExpenseId);
            return "redirect:/settings?expense_success";

        } catch (Exception e) {

            return "redirect:/settings?expense_error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-type-income")
    public String addTypeIncome(@RequestParam(name = "type_income_name") String typeIncomeName) {

        TypeIncome typeIncome = new TypeIncome();
        typeIncome.setName(typeIncomeName);
        typeIncomeService.addTypeIncome(typeIncome);

        return "redirect:/settings?income_success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-type-income")
    public String updateTypeIncome(@RequestParam(name = "type_income_id") Long typeIncomeId,
                                    @RequestParam(name = "type_income_name") String typeIncomeName) {

        TypeIncome typeIncome = typeIncomeService.getTypeIncome(typeIncomeId);

        if (typeIncome != null) {
            typeIncome.setName(typeIncomeName);
            typeIncomeService.updateTypeIncome(typeIncome);

            return "redirect:/settings?income_success";
        }
        return "redirect:/settings?income_error";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-type-income")
    public String deleteTypeIncome(@RequestParam(name = "type_income_id") Long typeIncomeId) {

        try {
            typeIncomeService.deleteTypeIncome(typeIncomeId);
            return "redirect:/settings?income_success";

        } catch (Exception e) {

            return "redirect:/settings?income_error";
        }
    }

}
