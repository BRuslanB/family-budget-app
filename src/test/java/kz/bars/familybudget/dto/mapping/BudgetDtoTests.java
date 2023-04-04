package kz.bars.familybudget.dto.mapping;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;

@SpringBootTest
@ActiveProfiles("test")
//@Component
//@RequiredArgsConstructor
public class BudgetDtoTests {

	@Autowired
	BudgetService budgetService;

//	private final BudgetService budgetService;

	@Test
	public void testBudgetToDto() {
		/*Arrange*/
		Budget budget = new Budget();
		budget.setId(BigInteger.valueOf(35));
		budget.setIncome("Новый доход");
		budget.setDescription("Описание нового дохода");

		/*Act*/
		BudgetDto budgetDto = budgetService.toDto(budget);

		/*Assert*/
		Assertions.assertNotNull(budgetDto);
		Assertions.assertEquals(budgetDto.getId(), budget.getId());
		Assertions.assertEquals(budgetDto.getIncome(), budget.getIncome());
		Assertions.assertEquals(budgetDto.getDescription(), budget.getDescription());
	}

}
