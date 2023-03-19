package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.service.ExpenseCategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@Service
public class ExpenseCategoryServiceTests {

	@Autowired
	ExpenseCategoryRepo expenseCategoryRepo;

	@Autowired
	ExpenseCategoryService expenseCategoryService;

	@Test
	public void checkExpenseCategoryAdd() {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");

		expenseCategoryRepo.deleteAll();
		expenseCategoryService.addExpenseCategory(expenseCategory);

		List<ExpenseCategory> expenseCategoryList = expenseCategoryRepo.findAll();
		Assertions.assertNotNull(expenseCategoryList);
		Assertions.assertTrue(expenseCategoryList.size() > 0);
		Assertions.assertNotNull(expenseCategoryList.get(0));
		Assertions.assertEquals(expenseCategoryList.get(0).getId(), expenseCategory.getId());
		Assertions.assertEquals(expenseCategoryList.get(0).getName(), expenseCategory.getName());
		Assertions.assertEquals(expenseCategoryList.get(0).getDescription(), expenseCategory.getDescription());

		expenseCategoryRepo.deleteAll();
	}

	@Test
	public void checkExpenseCategoryUpdate() {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");

		expenseCategoryRepo.deleteAll();
		expenseCategoryRepo.save(expenseCategory);

		Assertions.assertNotNull(expenseCategory);
		expenseCategory.setName("Измененная категория");
		expenseCategory.setDescription("Описание измененной категории");

		expenseCategoryService.updateExpenseCategory(expenseCategory);

		ExpenseCategory currentExpenseCategory = expenseCategoryRepo.findById(expenseCategory.getId()).orElse(null);
		Assertions.assertNotNull(currentExpenseCategory);
		Assertions.assertEquals(currentExpenseCategory.getId(), expenseCategory.getId());
		Assertions.assertEquals(currentExpenseCategory.getName(), expenseCategory.getName());
		Assertions.assertEquals(currentExpenseCategory.getDescription(), expenseCategory.getDescription());

		expenseCategoryRepo.deleteAll();
	}

	@Test
	public void checkExpenseCategoryDelete() {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");

		expenseCategoryRepo.deleteAll();
		expenseCategoryRepo.save(expenseCategory);

		ExpenseCategory currentExpenseCategory = expenseCategoryRepo.findById(expenseCategory.getId()).orElse(null);
		Assertions.assertNotNull(currentExpenseCategory);

		expenseCategoryService.deleteExpenseCategory(currentExpenseCategory.getId());

		List<ExpenseCategory> expenseCategoryList = expenseCategoryRepo.findAll();
		Assertions.assertTrue(expenseCategoryList.isEmpty());
	}

	@Test
	public void checkExpenseCategoryGetById() {
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");

		expenseCategoryRepo.deleteAll();
		expenseCategoryRepo.save(expenseCategory);
		ExpenseCategory currentExpenseCategory = expenseCategoryService.getExpenseCategory(expenseCategory.getId());

		Assertions.assertNotNull(currentExpenseCategory);
		Assertions.assertNotNull(currentExpenseCategory.getId());
		Assertions.assertEquals(currentExpenseCategory.getId(), expenseCategory.getId());
		Assertions.assertEquals(currentExpenseCategory.getName(), expenseCategory.getName());
		Assertions.assertEquals(currentExpenseCategory.getDescription(), expenseCategory.getDescription());

		expenseCategoryRepo.deleteAll();
	}

	@Test
	public void checkExpenseCategoryGetAll() {
		expenseCategoryRepo.deleteAll();
		List<ExpenseCategory> expenseCategoryList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ExpenseCategory expenseCategory = new ExpenseCategory();
			expenseCategory.setName("Новая категория " + i);
			expenseCategory.setDescription("Описание новой категории " + i);
			expenseCategoryList.add(expenseCategory);
			expenseCategoryRepo.save(expenseCategory);
		}

		List<ExpenseCategory> currentExpenseCategoryList = expenseCategoryService.getAllExpenseCategory();
		Assertions.assertFalse(currentExpenseCategoryList.isEmpty());

		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentExpenseCategoryList.get(i));
			Assertions.assertEquals(currentExpenseCategoryList.get(i).getName(), expenseCategoryList.get(i).getName());
			Assertions.assertEquals(currentExpenseCategoryList.get(i).getDescription(),
									expenseCategoryList.get(i).getDescription());
		}

		expenseCategoryRepo.deleteAll();
	}

}
