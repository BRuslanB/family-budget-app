package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.repository.BudgetRepo;
import kz.bars.familybudget.repository.CheckRepo;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.repository.PurchaseRepo;
import kz.bars.familybudget.service.BudgetService;
import kz.bars.familybudget.service.CheckService;
import kz.bars.familybudget.service.PurchaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
//@Service
public class CheckServiceTests {

	@Autowired
	CheckRepo checkRepo;

	@Autowired
	CheckService checkService;

	@Autowired
	BudgetRepo budgetRepo;

	@Autowired
	BudgetService budgetService;

	@Autowired
	PurchaseRepo purchaseRepo;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	ExpenseCategoryRepo expenseCategoryRepo;

	@Test
	public void checkCheckDtoAdd() {
		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		BudgetDto budgetDto = budgetService.toDto(budget);

		CheckDto checkDto1 = new CheckDto();
		checkDto1.setValue(2000.0);
		checkDto1.setDate(LocalDate.now());
		checkDto1.setNote("Комментарии дохода");
		checkDto1.setBudget(budgetDto);

		checkService.addCheckDto(checkDto1);

		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		PurchaseDto purchaseDto = purchaseService.toDto(purchase);

		CheckDto checkDto2 = new CheckDto();
		checkDto2.setValue(1000.0);
		checkDto2.setDate(LocalDate.now());
		checkDto2.setNote("Комментарии покупки");
		checkDto2.setPurchase(purchaseDto);

		checkService.addCheckDto(checkDto2);

		List<Check> checkList = checkRepo.findAll();
		Assertions.assertNotNull(checkList);
		Assertions.assertTrue(checkList.size() > 0);

		Assertions.assertNotNull(checkList.get(0));
//		Assertions.assertEquals(checkList.get(0).getId(), checkDto1.getId());
		Assertions.assertEquals(checkList.get(0).getValue(), checkDto1.getValue());
		Assertions.assertEquals(checkList.get(0).getDate(), checkDto1.getDate());
		Assertions.assertEquals(checkList.get(0).getNote(), checkDto1.getNote());
		Assertions.assertNotNull(checkList.get(0).getBudget());
		Assertions.assertEquals(checkList.get(0).getBudget().getId(), checkDto1.getBudget().getId());

		Assertions.assertNotNull(checkList.get(1));
//		Assertions.assertEquals(checkList.get(1).getId(), checkDto2.getId());
		Assertions.assertEquals(checkList.get(1).getValue(), checkDto2.getValue());
		Assertions.assertEquals(checkList.get(1).getDate(), checkDto2.getDate());
		Assertions.assertEquals(checkList.get(1).getNote(), checkDto2.getNote());
		Assertions.assertNotNull(checkList.get(1).getPurchase());
		Assertions.assertEquals(checkList.get(1).getPurchase().getId(), checkDto2.getPurchase().getId());

		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@Test
	public void checkCheckDtoUpdate() {
		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		BudgetDto budgetDto = budgetService.toDto(budget);

		Check check1 = new Check();
		check1.setValue(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);

		checkRepo.save(check1);

		CheckDto checkDto1 = new CheckDto();
		checkDto1.setId(check1.getId());
		checkDto1.setValue(2500.0);
		checkDto1.setDate(LocalDate.now());
		checkDto1.setNote("Новый комментарии дохода");
		checkDto1.setBudget(budgetDto);

		checkService.updateCheckDto(checkDto1);

		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		PurchaseDto purchaseDto = purchaseService.toDto(purchase);

		Check check2 = new Check();
		check2.setValue(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);

		checkRepo.save(check2);

		CheckDto checkDto2 = new CheckDto();
		checkDto2.setId(check2.getId());
		checkDto2.setValue(1500.0);
		checkDto2.setDate(LocalDate.now());
		checkDto2.setNote("Новый комментарии покупки");
		checkDto2.setPurchase(purchaseDto);

		checkService.updateCheckDto(checkDto2);

		List<Check> checkList = checkRepo.findAll();
		Assertions.assertNotNull(checkList);
		Assertions.assertTrue(checkList.size() > 0);

		Assertions.assertNotNull(checkList.get(0));
		Assertions.assertEquals(checkList.get(0).getId(), checkDto1.getId());
		Assertions.assertEquals(checkList.get(0).getValue(), checkDto1.getValue());
		Assertions.assertEquals(checkList.get(0).getDate(), checkDto1.getDate());
		Assertions.assertEquals(checkList.get(0).getNote(), checkDto1.getNote());
		Assertions.assertNotNull(checkList.get(0).getBudget());
		Assertions.assertEquals(checkList.get(0).getBudget().getId(), checkDto1.getBudget().getId());

		Assertions.assertNotNull(checkList.get(1));
		Assertions.assertEquals(checkList.get(1).getId(), checkDto2.getId());
		Assertions.assertEquals(checkList.get(1).getValue(), checkDto2.getValue());
		Assertions.assertEquals(checkList.get(1).getDate(), checkDto2.getDate());
		Assertions.assertEquals(checkList.get(1).getNote(), checkDto2.getNote());
		Assertions.assertNotNull(checkList.get(1).getPurchase());
		Assertions.assertEquals(checkList.get(1).getPurchase().getId(), checkDto2.getPurchase().getId());

		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@Test
	public void checkCheckDtoDelete() {
		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Check check1 = new Check();
		check1.setValue(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);

		checkRepo.save(check1);

		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		Check check2 = new Check();
		check2.setValue(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);

		checkRepo.save(check2);

		Check currentCheck1 = checkRepo.findById(check1.getId()).orElse(null);
		Assertions.assertNotNull(currentCheck1);

		checkService.deleteCheckDto(currentCheck1.getId());

		Check currentCheck2 = checkRepo.findById(check2.getId()).orElse(null);
		Assertions.assertNotNull(currentCheck2);

		checkService.deleteCheckDto(currentCheck2.getId());

		List<Check> checkList = checkRepo.findAll();
		Assertions.assertTrue(checkList.isEmpty());
	}

	@Test
	public void checkCheckDtoGetById() {
		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Check check1 = new Check();
		check1.setValue(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);

		checkRepo.save(check1);

		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		Check check2 = new Check();
		check2.setValue(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);

		checkRepo.save(check2);

		CheckDto checkDto1 = checkService.getCheckDto(check1.getId());

		Assertions.assertNotNull(checkDto1);
		Assertions.assertEquals(checkDto1.getId(), check1.getId());
		Assertions.assertEquals(checkDto1.getValue(), check1.getValue());
		Assertions.assertEquals(checkDto1.getDate(), check1.getDate());
		Assertions.assertEquals(checkDto1.getNote(), check1.getNote());
		Assertions.assertNotNull(checkDto1.getBudget());
		Assertions.assertEquals(checkDto1.getBudget().getId(), check1.getBudget().getId());

		CheckDto checkDto2 = checkService.getCheckDto(check2.getId());

		Assertions.assertNotNull(checkDto2);
		Assertions.assertEquals(checkDto2.getId(), check2.getId());
		Assertions.assertEquals(checkDto2.getValue(), check2.getValue());
		Assertions.assertEquals(checkDto2.getDate(), check2.getDate());
		Assertions.assertEquals(checkDto2.getNote(), check2.getNote());
		Assertions.assertNotNull(checkDto2.getPurchase());
		Assertions.assertEquals(checkDto2.getPurchase().getId(), check2.getPurchase().getId());

		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@Test
	public void checkCheckDtoGetAll() {
		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();

		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Check check1 = new Check();
		check1.setValue(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);

		checkRepo.save(check1);

		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		Check check2 = new Check();
		check2.setValue(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);

		checkRepo.save(check2);

		List<CheckDto> checkDtoList = checkService.getAllCheckDto();

		Assertions.assertNotNull(checkDtoList);
		Assertions.assertTrue(checkDtoList.size() > 0);

		Assertions.assertNotNull(checkDtoList.get(0));
		Assertions.assertEquals(checkDtoList.get(0).getId(), check1.getId());
		Assertions.assertEquals(checkDtoList.get(0).getValue(), check1.getValue());
		Assertions.assertEquals(checkDtoList.get(0).getDate(), check1.getDate());
		Assertions.assertEquals(checkDtoList.get(0).getNote(), check1.getNote());
		Assertions.assertNotNull(checkDtoList.get(0).getBudget());
		Assertions.assertEquals(checkDtoList.get(0).getBudget().getId(), check1.getBudget().getId());

		Assertions.assertNotNull(checkDtoList.get(1));
		Assertions.assertEquals(checkDtoList.get(1).getId(), check2.getId());
		Assertions.assertEquals(checkDtoList.get(1).getValue(), check2.getValue());
		Assertions.assertEquals(checkDtoList.get(1).getDate(), check2.getDate());
		Assertions.assertEquals(checkDtoList.get(1).getNote(), check2.getNote());
		Assertions.assertNotNull(checkDtoList.get(1).getPurchase());
		Assertions.assertEquals(checkDtoList.get(1).getPurchase().getId(), check2.getPurchase().getId());

		budgetRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		purchaseRepo.deleteAll();
		checkRepo.deleteAll();
	}

}
