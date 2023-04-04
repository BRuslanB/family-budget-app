package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.repository.BudgetRepo;
import kz.bars.familybudget.service.BudgetService;
import org.junit.jupiter.api.*;
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
public class BudgetServiceTests {

	@Autowired
	BudgetRepo budgetRepo;

	@Autowired
	BudgetService budgetService;

	@BeforeEach
	public void init() {
		budgetRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		budgetRepo.deleteAll();
	}

	@Test
	public void checkBudgetAdd() {
		/*Arrange*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");

		/*Act*/
		budgetService.addBudget(budget);

		/*Assert*/
		List<Budget> budgetList = budgetRepo.findAll();
		Assertions.assertNotNull(budgetList);
		Assertions.assertTrue(budgetList.size()>0);
		Assertions.assertNotNull(budgetList.get(0));
		Assertions.assertEquals(budgetList.get(0).getId(), budget.getId());
		Assertions.assertEquals(budgetList.get(0).getIncome(), budget.getIncome());
		Assertions.assertEquals(budgetList.get(0).getDescription(), budget.getDescription());
	}

	@Test
	public void checkBudgetUpdate() {
		/*Arrange*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		budget.setIncome("Измененный доход");
		budget.setDescription("Описание измененного дохода");

		/*Act*/
		budgetService.updateBudget(budget);

		/*Assert*/
		Budget currentBudget = budgetRepo.findById(budget.getId()).orElse(null);
		Assertions.assertNotNull(currentBudget);
		Assertions.assertEquals(currentBudget.getId(), budget.getId());
		Assertions.assertEquals(currentBudget.getIncome(), budget.getIncome());
		Assertions.assertEquals(currentBudget.getDescription(), budget.getDescription());
	}

	@Test
	public void checkBudgetDelete() {
		/*Arrange*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Budget currentBudget = budgetRepo.findById(budget.getId()).orElse(null);
		Assertions.assertNotNull(currentBudget);

		/*Act*/
		budgetService.deleteBudget(currentBudget.getId());

		/*Assert*/
		List<Budget> budgetList = budgetRepo.findAll();
		Assertions.assertTrue(budgetList.isEmpty());
	}

	@Test
	public void checkBudgetGetById() {
		/*Arrange*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		/*Act*/
		Budget currentBudget = budgetService.getBudget(budget.getId());

		/*Assert*/
		Assertions.assertNotNull(currentBudget);
		Assertions.assertEquals(currentBudget.getId(), budget.getId());
		Assertions.assertEquals(currentBudget.getIncome(), budget.getIncome());
		Assertions.assertEquals(currentBudget.getDescription(), budget.getDescription());
	}

	@Test
	public void checkBudgetGetAll() {
		/*Arrange*/
		List<Budget> budgetList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Budget budget = new Budget();
			budget.setIncome("Новый доход " + i);
			budget.setDescription("Описание нового дохода " + i);
			budgetList.add(budget);
			budgetRepo.save(budget);
		}

		/*Act*/
		List<Budget> currentBudgetList = budgetService.getAllBudget();

		/*Assert*/
		Assertions.assertFalse(currentBudgetList.isEmpty());
		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentBudgetList.get(i));
			Assertions.assertEquals(currentBudgetList.get(i).getIncome(), budgetList.get(i).getIncome());
			Assertions.assertEquals(currentBudgetList.get(i).getDescription(), budgetList.get(i).getDescription());
		}
	}

}
