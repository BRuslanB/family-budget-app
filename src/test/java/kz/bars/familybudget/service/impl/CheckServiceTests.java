package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.IncomeDto;
import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.dto.ExpenseDto;
import kz.bars.familybudget.model.Expense;
import kz.bars.familybudget.model.Income;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.repository.IncomeRepo;
import kz.bars.familybudget.repository.CheckRepo;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.repository.ExpenseRepo;
import kz.bars.familybudget.service.IncomeService;
import kz.bars.familybudget.service.CheckService;
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

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CheckServiceTests {

	@Autowired
	CheckRepo checkRepo;

	@Autowired
	CheckService checkService;

	@Autowired
    IncomeRepo incomeRepo;

	@Autowired
	IncomeService incomeService;

	@Autowired
	ExpenseRepo expenseRepo;

	@Autowired
	ExpenseService expenseService;

	@Autowired
	ExpenseCategoryRepo expenseCategoryRepo;

	@BeforeEach
	public void init() {
		incomeRepo.deleteAll();
		expenseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		incomeRepo.deleteAll();
		expenseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();
	}

	@Test
	public void checkCheckDtoAdd() {
		/*Arrange*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		IncomeDto incomeDto = incomeService.toDto(income);

		CheckDto checkDto1 = new CheckDto();
		checkDto1.setVal(2000.0);
		checkDto1.setDate(LocalDate.now());
		checkDto1.setNote("Комментарии дохода");
		checkDto1.setIncome(incomeDto);

		checkService.addCheckDto(checkDto1);

		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		ExpenseDto expenseDto = expenseService.toDto(expense);

		CheckDto checkDto2 = new CheckDto();
		checkDto2.setVal(1000.0);
		checkDto2.setDate(LocalDate.now());
		checkDto2.setNote("Комментарии покупки");
		checkDto2.setExpense(expenseDto);

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
		Assertions.assertNotNull(checkList.get(0).getIncome());
		Assertions.assertEquals(checkList.get(0).getIncome().getId(), checkDto1.getIncome().getId());

		Assertions.assertNotNull(checkList.get(1));
		Assertions.assertEquals(checkList.get(1).getVal(), checkDto2.getVal());
		Assertions.assertEquals(checkList.get(1).getDate(), checkDto2.getDate());
		Assertions.assertEquals(checkList.get(1).getNote(), checkDto2.getNote());
		Assertions.assertNotNull(checkList.get(1).getExpense());
		Assertions.assertEquals(checkList.get(1).getExpense().getId(), checkDto2.getExpense().getId());
	}

	@Test
	public void checkCheckDtoUpdate() {
		/*Arrange checkDto1*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		IncomeDto incomeDto = incomeService.toDto(income);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setIncome(income);
		checkRepo.save(check1);

		CheckDto checkDto1 = new CheckDto();
		checkDto1.setId(check1.getId());
		checkDto1.setVal(2500.0);
		checkDto1.setDate(LocalDate.now());
		checkDto1.setNote("Новый комментарии дохода");
		checkDto1.setIncome(incomeDto);

		/*Arrange checkDto2*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		ExpenseDto expenseDto = expenseService.toDto(expense);

		Check check2 = new Check();
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setExpense(expense);
		checkRepo.save(check2);

		CheckDto checkDto2 = new CheckDto();
		checkDto2.setId(check2.getId());
		checkDto2.setVal(1500.0);
		checkDto2.setDate(LocalDate.now());
		checkDto2.setNote("Новый комментарии покупки");
		checkDto2.setExpense(expenseDto);

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
		Assertions.assertNotNull(checkList.get(0).getIncome());
		Assertions.assertEquals(checkList.get(0).getIncome().getId(), checkDto1.getIncome().getId());

		Assertions.assertNotNull(checkList.get(1));
		Assertions.assertEquals(checkList.get(1).getId(), checkDto2.getId());
		Assertions.assertEquals(checkList.get(1).getVal(), checkDto2.getVal());
		Assertions.assertEquals(checkList.get(1).getDate(), checkDto2.getDate());
		Assertions.assertEquals(checkList.get(1).getNote(), checkDto2.getNote());
		Assertions.assertNotNull(checkList.get(1).getExpense());
		Assertions.assertEquals(checkList.get(1).getExpense().getId(), checkDto2.getExpense().getId());
	}

	@Test
	public void checkCheckDtoDelete() {
		/*Arrange check1*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setIncome(income);
		checkRepo.save(check1);

		Check currentCheck1 = checkRepo.findById(check1.getId()).orElse(null);
		Assertions.assertNotNull(currentCheck1);

		/*Arrange check2*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		Check check2 = new Check();
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setExpense(expense);
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
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setIncome(income);
		checkRepo.save(check1);

		/*Arrange check2*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		Check check2 = new Check();
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setExpense(expense);
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
		Assertions.assertNotNull(checkDto1.getIncome());
		Assertions.assertEquals(checkDto1.getIncome().getId(), check1.getIncome().getId());

		/*Assert checkDto2*/
		Assertions.assertNotNull(checkDto2);
		Assertions.assertEquals(checkDto2.getId(), check2.getId());
		Assertions.assertEquals(checkDto2.getVal(), check2.getVal());
		Assertions.assertEquals(checkDto2.getDate(), check2.getDate());
		Assertions.assertEquals(checkDto2.getNote(), check2.getNote());
		Assertions.assertNotNull(checkDto2.getExpense());
		Assertions.assertEquals(checkDto2.getExpense().getId(), check2.getExpense().getId());
	}

	@Test
	public void checkCheckDtoGetAll() {
		incomeRepo.deleteAll();
		expenseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();

		/*Arrange check1*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		Check check1 = new Check();
		check1.setVal(2000.0);
		check1.setDate(LocalDate.now());
		check1.setNote("Комментарии дохода");
		check1.setIncome(income);
		checkRepo.save(check1);

		/*Arrange check2*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Expense expense = new Expense();
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");
		expense.setCategory(expenseCategory);
		expenseRepo.save(expense);

		Check check2 = new Check();
		check2.setVal(1000.0);
		check2.setDate(LocalDate.now());
		check2.setNote("Комментарии покупки");
		check2.setExpense(expense);
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
		Assertions.assertNotNull(checkDtoList.get(0).getIncome());
		Assertions.assertEquals(checkDtoList.get(0).getIncome().getId(), check1.getIncome().getId());

		Assertions.assertNotNull(checkDtoList.get(1));
		Assertions.assertEquals(checkDtoList.get(1).getId(), check2.getId());
		Assertions.assertEquals(checkDtoList.get(1).getVal(), check2.getVal());
		Assertions.assertEquals(checkDtoList.get(1).getDate(), check2.getDate());
		Assertions.assertEquals(checkDtoList.get(1).getNote(), check2.getNote());
		Assertions.assertNotNull(checkDtoList.get(1).getExpense());
		Assertions.assertEquals(checkDtoList.get(1).getExpense().getId(), check2.getExpense().getId());

		incomeRepo.deleteAll();
		expenseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
		checkRepo.deleteAll();
	}

}
