package kz.bars.familybudget.dto.mapping;

import kz.bars.familybudget.dto.ExpenseCategoryDto;
import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.mapper.ExpenseCategoryMapper;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.service.PurchaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
//@Component
public class PurchaseDtoTests {

	@Autowired
	ExpenseCategoryMapper expenseCategoryMapper;

	@Autowired
	PurchaseService purchaseService;

	@Test
	public void testPurchaseToDto() {
		Purchase purchase = new Purchase();
		purchase.setId(25L);
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");

		PurchaseDto purchaseDto = purchaseService.toDto(purchase);
		ExpenseCategoryDto expenseCategoryDto = expenseCategoryMapper.toDto(purchase.getCategory());

		Assertions.assertNotNull(purchaseDto);
		Assertions.assertEquals(purchaseDto.getId(), purchase.getId());
		Assertions.assertEquals(purchaseDto.getExpense(), purchase.getExpense());
		Assertions.assertEquals(purchaseDto.getDescription(), purchase.getDescription());
		Assertions.assertEquals(purchaseDto.getCategory(), expenseCategoryDto);
	}

}
