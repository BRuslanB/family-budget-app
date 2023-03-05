package kz.bars.familybudget.api;

import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
@CrossOrigin
public class ExpenseCategoryController {

//    private final ExpenseCategoryService expenseCategoryService;
//
//    @GetMapping
//    public List<ExpenseCategory> getAllExpenseCategory() {
//        return expenseCategoryService.getAllExpenseCategory();
//    }
//
//    @GetMapping(value = "{id}")
//    public ExpenseCategory getExpenseCategory(@PathVariable(name = "id") Long id) {
//        return expenseCategoryService.getExpenseCategory(id);
//    }
//
//    @PostMapping
//    public ExpenseCategory addExpenseCategory(@RequestBody ExpenseCategory expenseCategory) {
//        return expenseCategoryService.addExpenseCategory(expenseCategory);
//    }
//
//    @PutMapping
//    public ExpenseCategory updateExpenseCategory(@RequestBody ExpenseCategory expenseCategory) {
//        return expenseCategoryService.updateExpenseCategory(expenseCategory);
//    }
//
//    @DeleteMapping(value = "{id}")
//    public void deleteExpenseCategory(@PathVariable(name = "id") Long id) {
//        expenseCategoryService.deleteExpenseCategory(id);
//    }

}
