package kz.bars.familybudget.dto.mapping;

import kz.bars.familybudget.dto.ExpenseCategoryDto;
import kz.bars.familybudget.mapper.ExpenseCategoryMapper;
import kz.bars.familybudget.model.ExpenseCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles("test")
public class ExpenseCategoryDtoTests {

	@Autowired
	ExpenseCategoryMapper expenseCategoryMapper;

	@Test
	public void testExpenseCategoryMapToDto() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setId(Long.valueOf(55));
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");

		/*Act*/
		ExpenseCategoryDto expenseCategoryDto = expenseCategoryMapper.toDto(expenseCategory);

		/*Assert*/
		Assertions.assertNotNull(expenseCategoryDto);
		Assertions.assertEquals(expenseCategory.getId(), expenseCategoryDto.getId());
		Assertions.assertEquals(expenseCategory.getName(), expenseCategoryDto.getName());
		Assertions.assertEquals(expenseCategory.getDescription(), expenseCategoryDto.getDescription());
	}

}
