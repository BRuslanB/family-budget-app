package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.Income;
import kz.bars.familybudget.repository.IncomeRepo;
import kz.bars.familybudget.service.IncomeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class IncomeServiceTests {

	@Autowired
    IncomeRepo incomeRepo;

	@Autowired
	IncomeService incomeService;

	@BeforeEach
	public void init() {
		incomeRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		incomeRepo.deleteAll();
	}

	@Test
	public void checkBudgetAdd() {
		/*Arrange*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");

		/*Act*/
		incomeService.addIncome(income);

		/*Assert*/
		List<Income> incomeList = incomeRepo.findAll();
		Assertions.assertNotNull(incomeList);
		Assertions.assertTrue(incomeList.size()>0);
		Assertions.assertNotNull(incomeList.get(0));
		Assertions.assertEquals(incomeList.get(0).getId(), income.getId());
		Assertions.assertEquals(incomeList.get(0).getName(), income.getName());
		Assertions.assertEquals(incomeList.get(0).getDescription(), income.getDescription());
	}

	@Test
	public void checkBudgetUpdate() {
		/*Arrange*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		income.setName("Измененный доход");
		income.setDescription("Описание измененного дохода");

		/*Act*/
		incomeService.updateIncome(income);

		/*Assert*/
		Income currentIncome = incomeRepo.findById(income.getId()).orElse(null);
		Assertions.assertNotNull(currentIncome);
		Assertions.assertEquals(currentIncome.getId(), income.getId());
		Assertions.assertEquals(currentIncome.getName(), income.getName());
		Assertions.assertEquals(currentIncome.getDescription(), income.getDescription());
	}

	@Test
	public void checkBudgetDelete() {
		/*Arrange*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		Income currentIncome = incomeRepo.findById(income.getId()).orElse(null);
		Assertions.assertNotNull(currentIncome);

		/*Act*/
		incomeService.deleteIncome(currentIncome.getId());

		/*Assert*/
		List<Income> incomeList = incomeRepo.findAll();
		Assertions.assertTrue(incomeList.isEmpty());
	}

	@Test
	public void checkBudgetGetById() {
		/*Arrange*/
		Income income = new Income();
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");
		incomeRepo.save(income);

		/*Act*/
		Income currentIncome = incomeService.getIncome(income.getId());

		/*Assert*/
		Assertions.assertNotNull(currentIncome);
		Assertions.assertEquals(currentIncome.getId(), income.getId());
		Assertions.assertEquals(currentIncome.getName(), income.getName());
		Assertions.assertEquals(currentIncome.getDescription(), income.getDescription());
	}

	@Test
	public void checkBudgetGetAll() {
		/*Arrange*/
		List<Income> incomeList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Income income = new Income();
			income.setName("Новый доход " + i);
			income.setDescription("Описание нового дохода " + i);
			incomeList.add(income);
			incomeRepo.save(income);
		}

		/*Act*/
		List<Income> currentIncomeList = incomeService.getAllIncome();

		/*Assert*/
		Assertions.assertFalse(currentIncomeList.isEmpty());
		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentIncomeList.get(i));
			Assertions.assertEquals(currentIncomeList.get(i).getName(), incomeList.get(i).getName());
			Assertions.assertEquals(currentIncomeList.get(i).getDescription(), incomeList.get(i).getDescription());
		}
	}

}
