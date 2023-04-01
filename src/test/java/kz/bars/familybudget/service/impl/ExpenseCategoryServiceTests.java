package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.service.ExpenseCategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@Service
public class ExpenseCategoryServiceTests {

	@Autowired
	ExpenseCategoryRepo expenseCategoryRepo;

	@Autowired
	ExpenseCategoryService expenseCategoryService;

	@BeforeEach
	public void init() {
		expenseCategoryRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		expenseCategoryRepo.deleteAll();
	}

	@Test
	public void checkExpenseCategoryAdd() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");

		/*Act*/
		expenseCategoryService.addExpenseCategory(expenseCategory);

		/*Assert*/
		List<ExpenseCategory> expenseCategoryList = expenseCategoryRepo.findAll();
		Assertions.assertNotNull(expenseCategoryList);
		Assertions.assertTrue(expenseCategoryList.size() > 0);
		Assertions.assertNotNull(expenseCategoryList.get(0));
		Assertions.assertEquals(expenseCategoryList.get(0).getId(), expenseCategory.getId());
		Assertions.assertEquals(expenseCategoryList.get(0).getName(), expenseCategory.getName());
		Assertions.assertEquals(expenseCategoryList.get(0).getDescription(), expenseCategory.getDescription());
	}

	@Test
	public void checkExpenseCategoryUpdate() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Assertions.assertNotNull(expenseCategory);
		expenseCategory.setName("Измененная категория");
		expenseCategory.setDescription("Описание измененной категории");

		/*Act*/
		expenseCategoryService.updateExpenseCategory(expenseCategory);

		/*Assert*/
		ExpenseCategory currentExpenseCategory = expenseCategoryRepo.findById(expenseCategory.getId()).orElse(null);
		Assertions.assertNotNull(currentExpenseCategory);
		Assertions.assertEquals(currentExpenseCategory.getId(), expenseCategory.getId());
		Assertions.assertEquals(currentExpenseCategory.getName(), expenseCategory.getName());
		Assertions.assertEquals(currentExpenseCategory.getDescription(), expenseCategory.getDescription());
	}

	@Test
	public void checkExpenseCategoryDelete() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		ExpenseCategory currentExpenseCategory = expenseCategoryRepo.findById(expenseCategory.getId()).orElse(null);
		Assertions.assertNotNull(currentExpenseCategory);

		/*Act*/
		expenseCategoryService.deleteExpenseCategory(currentExpenseCategory.getId());

		/*Assert*/
		List<ExpenseCategory> expenseCategoryList = expenseCategoryRepo.findAll();
		Assertions.assertTrue(expenseCategoryList.isEmpty());
	}

	@Test
	public void checkExpenseCategoryGetById() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		/*Act*/
		ExpenseCategory currentExpenseCategory = expenseCategoryService.getExpenseCategory(expenseCategory.getId());

		/*Assert*/
		Assertions.assertNotNull(currentExpenseCategory);
		Assertions.assertNotNull(currentExpenseCategory.getId());
		Assertions.assertEquals(currentExpenseCategory.getId(), expenseCategory.getId());
		Assertions.assertEquals(currentExpenseCategory.getName(), expenseCategory.getName());
		Assertions.assertEquals(currentExpenseCategory.getDescription(), expenseCategory.getDescription());
	}

	@Test
	public void checkExpenseCategoryGetAll() {
		/*Arrange*/
		List<ExpenseCategory> expenseCategoryList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ExpenseCategory expenseCategory = new ExpenseCategory();
			expenseCategory.setName("Новая категория " + i);
			expenseCategory.setDescription("Описание новой категории " + i);
			expenseCategoryList.add(expenseCategory);
			expenseCategoryRepo.save(expenseCategory);
		}

		/*Act*/
		List<ExpenseCategory> currentExpenseCategoryList = expenseCategoryService.getAllExpenseCategory();

		/*Assert*/
		Assertions.assertFalse(currentExpenseCategoryList.isEmpty());
		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentExpenseCategoryList.get(i));
			Assertions.assertEquals(currentExpenseCategoryList.get(i).getName(), expenseCategoryList.get(i).getName());
			Assertions.assertEquals(currentExpenseCategoryList.get(i).getDescription(),
									expenseCategoryList.get(i).getDescription());
		}
	}

}
