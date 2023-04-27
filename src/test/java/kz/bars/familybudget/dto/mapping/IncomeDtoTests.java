package kz.bars.familybudget.dto.mapping;

import kz.bars.familybudget.dto.IncomeDto;
import kz.bars.familybudget.model.Income;
import kz.bars.familybudget.service.IncomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles("test")
public class IncomeDtoTests {

	@Autowired
    IncomeService incomeService;

	@Test
	public void testIncomeToDto() {
		/*Arrange*/
		Income income = new Income();
		income.setId(Long.valueOf(35));
		income.setName("Новый доход");
		income.setDescription("Описание нового дохода");

		/*Act*/
		IncomeDto incomeDto = incomeService.toDto(income);

		/*Assert*/
		Assertions.assertNotNull(incomeDto);
		Assertions.assertEquals(incomeDto.getId(), income.getId());
		Assertions.assertEquals(incomeDto.getName(), income.getName());
		Assertions.assertEquals(incomeDto.getDescription(), income.getDescription());
	}

}
