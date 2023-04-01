package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.repository.BudgetRepo;
import kz.bars.familybudget.service.BudgetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@Service
public class BudgetServiceTests {

	@Autowired
	BudgetRepo budgetRepo;

	@Autowired
	BudgetService budgetService;

	@Test
	public void checkBudgetAdd() {
		budgetRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");

		budgetService.addBudget(budget);

		List<Budget> budgetList = budgetRepo.findAll();
		Assertions.assertNotNull(budgetList);
		Assertions.assertTrue(budgetList.size()>0);
		Assertions.assertNotNull(budgetList.get(0));
		Assertions.assertEquals(budgetList.get(0).getId(), budget.getId());
		Assertions.assertEquals(budgetList.get(0).getIncome(), budget.getIncome());
		Assertions.assertEquals(budgetList.get(0).getDescription(), budget.getDescription());

		budgetRepo.deleteAll();
	}

	@Test
	public void checkBudgetUpdate() {
		budgetRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");

		budgetRepo.save(budget);

		budget.setIncome("Измененный доход");
		budget.setDescription("Описание измененного дохода");

		budgetService.updateBudget(budget);

		Budget currentBudget = budgetRepo.findById(budget.getId()).orElse(null);
		Assertions.assertNotNull(currentBudget);
		Assertions.assertEquals(currentBudget.getId(), budget.getId());
		Assertions.assertEquals(currentBudget.getIncome(), budget.getIncome());
		Assertions.assertEquals(currentBudget.getDescription(), budget.getDescription());

		budgetRepo.deleteAll();
	}

	@Test
	public void checkBudgetDelete() {
		budgetRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");

		budgetRepo.save(budget);

		Budget currentBudget = budgetRepo.findById(budget.getId()).orElse(null);
		Assertions.assertNotNull(currentBudget);

		budgetService.deleteBudget(currentBudget.getId());

		List<Budget> budgetList = budgetRepo.findAll();
		Assertions.assertTrue(budgetList.isEmpty());
	}

	@Test
	public void checkBudgetGetById() {
		budgetRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");

		budgetRepo.save(budget);

		Budget currentBudget = budgetService.getBudget(budget.getId());
		Assertions.assertNotNull(currentBudget);
		Assertions.assertEquals(currentBudget.getId(), budget.getId());
		Assertions.assertEquals(currentBudget.getIncome(), budget.getIncome());
		Assertions.assertEquals(currentBudget.getDescription(), budget.getDescription());

		budgetRepo.deleteAll();
	}

	@Test
	public void checkBudgetGetAll() {
		budgetRepo.deleteAll();

		List<Budget> budgetList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Budget budget = new Budget();
			budget.setIncome("Новый доход " + i);
			budget.setDescription("Описание нового дохода " + i);
			budgetList.add(budget);
			budgetRepo.save(budget);
		}

		List<Budget> currentBudgetList = budgetService.getAllBudget();
		Assertions.assertFalse(currentBudgetList.isEmpty());

		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentBudgetList.get(i));
			Assertions.assertEquals(currentBudgetList.get(i).getIncome(), budgetList.get(i).getIncome());
			Assertions.assertEquals(currentBudgetList.get(i).getDescription(), budgetList.get(i).getDescription());
		}

		budgetRepo.deleteAll();
	}

}
