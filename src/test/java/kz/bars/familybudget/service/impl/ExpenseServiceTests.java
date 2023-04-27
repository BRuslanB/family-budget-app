package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.Expense;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.repository.ExpenseRepo;
import kz.bars.familybudget.service.ExpenseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ExpenseServiceTests {

	@Autowired
	ExpenseRepo expenseRepo;

	@Autowired
	ExpenseCategoryRepo expenseCategoryRepo;

	@Autowired
	ExpenseService expenseService;

	@BeforeEach
	public void init() {
		expenseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		expenseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
	}

	@Test
	public void checkExpenseAdd() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);

		/*Act*/
		expenseService.addExpense(expense);

		/*Assert*/
		List<Expense> expenseList = expenseRepo.findAll();
		Assertions.assertNotNull(expenseList);
		Assertions.assertTrue(expenseList.size()>0);
		Assertions.assertNotNull(expenseList.get(0));
		Assertions.assertEquals(expenseList.get(0).getId(), expense.getId());
		Assertions.assertEquals(expenseList.get(0).getName(), expense.getName());
		Assertions.assertEquals(expenseList.get(0).getDescription(), expense.getDescription());
		Assertions.assertNotNull(expenseList.get(0).getCategory());
		Assertions.assertEquals(expenseList.get(0).getCategory().getName(), expense.getCategory().getName());
		Assertions.assertEquals(expenseList.get(0).getCategory().getDescription(),
								expense.getCategory().getDescription());
	}

	@Test
	public void checkExpenseUpdate() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		ExpenseCategory otherExpenseCategory = new ExpenseCategory();
		otherExpenseCategory.setName("Другая категория");
		otherExpenseCategory.setDescription("Описание другой категории");
		expenseCategoryRepo.save(otherExpenseCategory);

		expense.setName("Измененная покупка");
		expense.setDescription("Описание измененной покупки");
		expense.setCategory(otherExpenseCategory);

		/*Act*/
		expenseService.updateExpense(expense);

		/*Assert*/
		Expense currentExpense = expenseRepo.findById(expense.getId()).orElse(null);
		Assertions.assertNotNull(currentExpense);
		Assertions.assertEquals(currentExpense.getId(), expense.getId());
		Assertions.assertEquals(currentExpense.getName(), expense.getName());
		Assertions.assertEquals(currentExpense.getDescription(), expense.getDescription());
		Assertions.assertNotNull(currentExpense.getCategory());
		Assertions.assertEquals(currentExpense.getCategory().getName(), expense.getCategory().getName());
		Assertions.assertEquals(currentExpense.getCategory().getDescription(), expense.getCategory().getDescription());
	}

	@Test
	public void checkExpenseDelete() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		Expense currentExpense = expenseRepo.findById(expense.getId()).orElse(null);
		Assertions.assertNotNull(currentExpense);

		/*Act*/
		expenseService.deleteExpense(currentExpense.getId());

		/*Assert*/
		List<Expense> expenseList = expenseRepo.findAll();
		Assertions.assertTrue(expenseList.isEmpty());
	}

	@Test
	public void checkExpenseGetById() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		/*Act*/
		Expense currentExpense = expenseService.getExpense(expense.getId());

		/*Assert*/
		Assertions.assertNotNull(currentExpense);
		Assertions.assertEquals(currentExpense.getId(), expense.getId());
		Assertions.assertEquals(currentExpense.getName(), expense.getName());
		Assertions.assertEquals(currentExpense.getDescription(), expense.getDescription());
		Assertions.assertNotNull(currentExpense.getCategory());
		Assertions.assertEquals(currentExpense.getCategory().getName(), expense.getCategory().getName());
		Assertions.assertEquals(currentExpense.getCategory().getDescription(), expense.getCategory().getDescription());
	}

	@Test
	public void checkExpenseGetAll() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		List<Expense> expenseList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Expense expense = new Expense();
			expense.setName("Новая покупка " + i);
			expense.setDescription("Описание новой покупки " + i);
			expense.setCategory(expenseCategory);
			expenseList.add(expense);
			expenseRepo.save(expense);
		}

		/*Act*/
		List<Expense> currentExpenseList = expenseService.getAllExpense();

		/*Assert*/
		Assertions.assertFalse(currentExpenseList.isEmpty());
		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentExpenseList.get(i));
			Assertions.assertEquals(currentExpenseList.get(i).getName(), expenseList.get(i).getName());
			Assertions.assertEquals(currentExpenseList.get(i).getDescription(), expenseList.get(i).getDescription());
			Assertions.assertNotNull(currentExpenseList.get(i).getCategory());
			Assertions.assertEquals(currentExpenseList.get(i).getCategory().getName(),
									expenseList.get(i).getCategory().getName());
			Assertions.assertEquals(currentExpenseList.get(i).getCategory().getDescription(),
									expenseList.get(i).getCategory().getDescription());
		}
	}

}
