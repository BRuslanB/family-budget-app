package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.CategoryExpenseDto;
import kz.bars.familybudget.model.CategoryExpense;

import java.util.List;

public interface CategoryExpenseService {

    public List<CategoryExpense> getAllCategoryExpense();
    public CategoryExpense getCategoryExpense(Long id);
    public CategoryExpense addCategoryExpense(CategoryExpense categoryExpense);
    public CategoryExpense updateCategoryExpense(CategoryExpense categoryExpense);
    public void deleteCategoryExpense(Long id);
    public List<CategoryExpenseDto> getAllCategoryExpenseDto();
    public CategoryExpenseDto getCategoryExpenseDto(Long id);
    public CategoryExpenseDto addCategoryExpenseDto(CategoryExpenseDto categoryExpenseDto);
    public CategoryExpenseDto updateCategoryExpenseDto(CategoryExpenseDto categoryExpenseDto);

}
