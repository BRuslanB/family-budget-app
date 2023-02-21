package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.CategoryExpenseDto;
import kz.bars.familybudget.mapper.CategoryExpenseMapper;
import kz.bars.familybudget.model.CategoryExpense;
import kz.bars.familybudget.repository.CategoryExpenseRepo;
import kz.bars.familybudget.service.CategoryExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryExpenseServiceImpl implements CategoryExpenseService {

    private final CategoryExpenseRepo categoryExpenseRepo;
    private final CategoryExpenseMapper categoryExpenseMapper;

    @Override
    public List<CategoryExpense> getAllCategoryExpense() {
        return categoryExpenseRepo.findAll();
    }

    @Override
    public CategoryExpense getCategoryExpense(Long id) {
        return categoryExpenseRepo.findById(id).orElseThrow();
    }

    @Override
    public CategoryExpense addCategoryExpense(CategoryExpense categoryExpense) {
        return categoryExpenseRepo.save(categoryExpense);
    }

    @Override
    public CategoryExpense updateCategoryExpense(CategoryExpense categoryExpense) {
        return categoryExpenseRepo.save(categoryExpense);
    }

    @Override
    public void deleteCategoryExpense(Long id) {
        categoryExpenseRepo.deleteById(id);
    }

    @Override
    public List<CategoryExpenseDto> getAllCategoryExpenseDto() {
        return categoryExpenseMapper.toDtoList(categoryExpenseRepo.findAll());
    }

    @Override
    public CategoryExpenseDto getCategoryExpenseDto(Long id) {
        return categoryExpenseMapper.toDto(categoryExpenseRepo.findById(id).orElseThrow());
    }

    @Override
    public CategoryExpenseDto addCategoryExpenseDto(CategoryExpenseDto categoryExpenseDto) {
        return categoryExpenseMapper.toDto(categoryExpenseRepo.save(categoryExpenseMapper.toEntity(categoryExpenseDto)));
    }

    @Override
    public CategoryExpenseDto updateCategoryExpenseDto(CategoryExpenseDto categoryExpenseDto) {
        return categoryExpenseMapper.toDto(categoryExpenseRepo.save(categoryExpenseMapper.toEntity(categoryExpenseDto)));
    }
}
