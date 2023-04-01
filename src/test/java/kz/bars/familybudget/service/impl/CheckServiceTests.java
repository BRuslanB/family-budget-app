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

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
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

	@BeforeEach
	public void init() {
		budgetRepo.deleteAll();
		purchaseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		budgetRepo.deleteAll();
		purchaseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@Test
	public void checkCheckDtoAdd() {
		/*Arrange*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		BudgetDto budgetDto = budgetService.toDto(budget);

		CheckDto checkDto1 = new CheckDto();
		checkDto1.setVal(2000.0);
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
		checkDto2.setVal(1000.0);
		checkDto2.setDate(LocalDate.now());
		checkDto2.setNote("Комментарии покупки");
		checkDto2.setPurchase(purchaseDto);

		/*Act*/
		checkService.addCheckDto(checkDto2);


		/*Assert*/
		List<Check> checkList = checkRepo.findAll();
		Assertions.assertNotNull(checkList);
		Assertions.assertTrue(checkList.size() > 0);

		Assertions.assertNotNull(checkList.get(0));
		Assertions.assertEquals(checkList.get(0).getVal(), checkDto1.getVal());
		Assertions.assertEquals(checkList.get(0).getDate(), checkDto1.getDate());
		Assertions.assertEquals(checkList.get(0).getNote(), checkDto1.getNote());
		Assertions.assertNotNull(checkList.get(0).getBudget());
		Assertions.assertEquals(checkList.get(0).getBudget().getId(), checkDto1.getBudget().getId());

		Assertions.assertNotNull(checkList.get(1));
		Assertions.assertEquals(checkList.get(1).getVal(), checkDto2.getVal());
		Assertions.assertEquals(checkList.get(1).getDate(), checkDto2.getDate());
		Assertions.assertEquals(checkList.get(1).getNote(), checkDto2.getNote());
		Assertions.assertNotNull(checkList.get(1).getPurchase());
		Assertions.assertEquals(checkList.get(1).getPurchase().getId(), checkDto2.getPurchase().getId());
	}

	@Test
	public void checkCheckDtoUpdate() {
		/*Arrange checkDto1*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		BudgetDto budgetDto = budgetService.toDto(budget);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);
		checkRepo.save(check1);

		CheckDto checkDto1 = new CheckDto();
		checkDto1.setId(check1.getId());
		checkDto1.setVal(2500.0);
		checkDto1.setDate(LocalDate.now());
		checkDto1.setNote("Новый комментарии дохода");
		checkDto1.setBudget(budgetDto);

		/*Arrange checkDto2*/
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
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);
		checkRepo.save(check2);

		CheckDto checkDto2 = new CheckDto();
		checkDto2.setId(check2.getId());
		checkDto2.setVal(1500.0);
		checkDto2.setDate(LocalDate.now());
		checkDto2.setNote("Новый комментарии покупки");
		checkDto2.setPurchase(purchaseDto);

		/*Act checkDto1 and checkDto2*/
		checkService.updateCheckDto(checkDto1);
		checkService.updateCheckDto(checkDto2);

		/*Assert checkDto1 and checkDto2*/
		List<Check> checkList = checkRepo.findAll();
		Assertions.assertNotNull(checkList);
		Assertions.assertTrue(checkList.size() > 0);

		Assertions.assertNotNull(checkList.get(0));
		Assertions.assertEquals(checkList.get(0).getId(), checkDto1.getId());
		Assertions.assertEquals(checkList.get(0).getVal(), checkDto1.getVal());
		Assertions.assertEquals(checkList.get(0).getDate(), checkDto1.getDate());
		Assertions.assertEquals(checkList.get(0).getNote(), checkDto1.getNote());
		Assertions.assertNotNull(checkList.get(0).getBudget());
		Assertions.assertEquals(checkList.get(0).getBudget().getId(), checkDto1.getBudget().getId());

		Assertions.assertNotNull(checkList.get(1));
		Assertions.assertEquals(checkList.get(1).getId(), checkDto2.getId());
		Assertions.assertEquals(checkList.get(1).getVal(), checkDto2.getVal());
		Assertions.assertEquals(checkList.get(1).getDate(), checkDto2.getDate());
		Assertions.assertEquals(checkList.get(1).getNote(), checkDto2.getNote());
		Assertions.assertNotNull(checkList.get(1).getPurchase());
		Assertions.assertEquals(checkList.get(1).getPurchase().getId(), checkDto2.getPurchase().getId());
	}

	@Test
	public void checkCheckDtoDelete() {
		/*Arrange check1*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);
		checkRepo.save(check1);

		Check currentCheck1 = checkRepo.findById(check1.getId()).orElse(null);
		Assertions.assertNotNull(currentCheck1);

		/*Arrange check2*/
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
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);
		checkRepo.save(check2);

		Check currentCheck2 = checkRepo.findById(check2.getId()).orElse(null);
		Assertions.assertNotNull(currentCheck2);

		/*Act check1 and check2*/
		checkService.deleteCheckDto(currentCheck1.getId());
		checkService.deleteCheckDto(currentCheck2.getId());

		/*Assert check1 and check2*/
		List<Check> checkList = checkRepo.findAll();
		Assertions.assertTrue(checkList.isEmpty());
	}

	@Test
	public void checkCheckDtoGetById() {
		/*Arrange check1*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);
		checkRepo.save(check1);

		/*Arrange check2*/
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
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);
		checkRepo.save(check2);

		/*Act checkDto1 and checkDto2*/
		CheckDto checkDto1 = checkService.getCheckDto(check1.getId());
		CheckDto checkDto2 = checkService.getCheckDto(check2.getId());

		/*Assert checkDto1*/
		Assertions.assertNotNull(checkDto1);
		Assertions.assertEquals(checkDto1.getId(), check1.getId());
		Assertions.assertEquals(checkDto1.getVal(), check1.getVal());
		Assertions.assertEquals(checkDto1.getDate(), check1.getDate());
		Assertions.assertEquals(checkDto1.getNote(), check1.getNote());
		Assertions.assertNotNull(checkDto1.getBudget());
		Assertions.assertEquals(checkDto1.getBudget().getId(), check1.getBudget().getId());

		/*Assert checkDto2*/
		Assertions.assertNotNull(checkDto2);
		Assertions.assertEquals(checkDto2.getId(), check2.getId());
		Assertions.assertEquals(checkDto2.getVal(), check2.getVal());
		Assertions.assertEquals(checkDto2.getDate(), check2.getDate());
		Assertions.assertEquals(checkDto2.getNote(), check2.getNote());
		Assertions.assertNotNull(checkDto2.getPurchase());
		Assertions.assertEquals(checkDto2.getPurchase().getId(), check2.getPurchase().getId());
	}

	@Test
	public void checkCheckDtoGetAll() {
		budgetRepo.deleteAll();
		purchaseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();

		/*Arrange check1*/
		Budget budget = new Budget();
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");
		budgetRepo.save(budget);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setBudget(budget);
		checkRepo.save(check1);

		/*Arrange check2*/
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
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setPurchase(purchase);
		checkRepo.save(check2);

		/*Act checkDtoList*/
		List<CheckDto> checkDtoList = checkService.getAllCheckDto();

		/*Assert checkDtoList*/
		Assertions.assertNotNull(checkDtoList);
		Assertions.assertTrue(checkDtoList.size() > 0);

		Assertions.assertNotNull(checkDtoList.get(0));
		Assertions.assertEquals(checkDtoList.get(0).getId(), check1.getId());
		Assertions.assertEquals(checkDtoList.get(0).getVal(), check1.getVal());
		Assertions.assertEquals(checkDtoList.get(0).getDate(), check1.getDate());
		Assertions.assertEquals(checkDtoList.get(0).getNote(), check1.getNote());
		Assertions.assertNotNull(checkDtoList.get(0).getBudget());
		Assertions.assertEquals(checkDtoList.get(0).getBudget().getId(), check1.getBudget().getId());

		Assertions.assertNotNull(checkDtoList.get(1));
		Assertions.assertEquals(checkDtoList.get(1).getId(), check2.getId());
		Assertions.assertEquals(checkDtoList.get(1).getVal(), check2.getVal());
		Assertions.assertEquals(checkDtoList.get(1).getDate(), check2.getDate());
		Assertions.assertEquals(checkDtoList.get(1).getNote(), check2.getNote());
		Assertions.assertNotNull(checkDtoList.get(1).getPurchase());
		Assertions.assertEquals(checkDtoList.get(1).getPurchase().getId(), check2.getPurchase().getId());

		budgetRepo.deleteAll();
		purchaseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();
	}

}
