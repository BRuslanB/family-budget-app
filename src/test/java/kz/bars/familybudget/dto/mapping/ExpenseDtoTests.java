package kz.bars.familybudget.dto.mapping;

import kz.bars.familybudget.dto.ExpenseCategoryDto;
import kz.bars.familybudget.dto.ExpenseDto;
import kz.bars.familybudget.mapper.ExpenseCategoryMapper;
import kz.bars.familybudget.model.Expense;
import kz.bars.familybudget.service.ExpenseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles("test")
public class ExpenseDtoTests {

	@Autowired
	ExpenseCategoryMapper expenseCategoryMapper;

	@Autowired
	ExpenseService expenseService;

	@Test
	public void testPurchaseToDto() {
		/*Arrange*/
		Expense expense = new Expense();
		expense.setId(Long.valueOf(25));
		expense.setName("Новая покупка");
		expense.setDescription("Описание новой покупки");

		/*Act*/
		ExpenseDto expenseDto = expenseService.toDto(expense);
		ExpenseCategoryDto expenseCategoryDto = expenseCategoryMapper.toDto(expense.getCategory());

		/*Assert*/
		Assertions.assertNotNull(expenseDto);
		Assertions.assertEquals(expenseDto.getId(), expense.getId());
		Assertions.assertEquals(expenseDto.getName(), expense.getName());
		Assertions.assertEquals(expenseDto.getDescription(), expense.getDescription());
		Assertions.assertEquals(expenseDto.getCategory(), expenseCategoryDto);
	}

}
