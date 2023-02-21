package kz.bars.familybudget.api;

import kz.bars.familybudget.model.CategoryExpense;
import kz.bars.familybudget.service.CategoryExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
@CrossOrigin
public class CategoryExpenseController {

    private final CategoryExpenseService categoryExpenseService;

    @GetMapping
    public List<CategoryExpense> getAllCategoryExpense() {
        return categoryExpenseService.getAllCategoryExpense();
    }

    @GetMapping(value = "{id}")
    public CategoryExpense getCategoryExpense(@PathVariable(name = "id") Long id) {
        return categoryExpenseService.getCategoryExpense(id);
    }

    @PostMapping
    public CategoryExpense addCategoryExpense(@RequestBody CategoryExpense categoryExpense) {
        return categoryExpenseService.addCategoryExpense(categoryExpense);
    }

    @PutMapping
    public CategoryExpense updateCategoryExpense(@RequestBody CategoryExpense categoryExpense) {
        return categoryExpenseService.updateCategoryExpense(categoryExpense);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCategoryExpense(@PathVariable(name = "id") Long id) {
        categoryExpenseService.deleteCategoryExpense(id);
    }

}
