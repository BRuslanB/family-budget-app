package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.repository.ExpenseCategoryRepo;
import kz.bars.familybudget.repository.PurchaseRepo;
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

import java.util.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@Service
public class PurchaseServiceTests {

	@Autowired
	PurchaseRepo purchaseRepo;

	@Autowired
	ExpenseCategoryRepo expenseCategoryRepo;

	@Autowired
	PurchaseService purchaseService;

	@BeforeEach
	public void init() {
		purchaseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
	}

	@AfterEach
	public void teardown() {
		purchaseRepo.deleteAll();
		expenseCategoryRepo.deleteAll();
	}

	@Test
	public void checkPurchaseAdd() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);

		/*Act*/
		purchaseService.addPurchase(purchase);

		/*Assert*/
		List<Purchase> purchaseList = purchaseRepo.findAll();
		Assertions.assertNotNull(purchaseList);
		Assertions.assertTrue(purchaseList.size()>0);
		Assertions.assertNotNull(purchaseList.get(0));
		Assertions.assertEquals(purchaseList.get(0).getId(), purchase.getId());
		Assertions.assertEquals(purchaseList.get(0).getExpense(), purchase.getExpense());
		Assertions.assertEquals(purchaseList.get(0).getDescription(), purchase.getDescription());
		Assertions.assertNotNull(purchaseList.get(0).getCategory());
		Assertions.assertEquals(purchaseList.get(0).getCategory().getName(), purchase.getCategory().getName());
		Assertions.assertEquals(purchaseList.get(0).getCategory().getDescription(),
								purchase.getCategory().getDescription());
	}

	@Test
	public void checkPurchaseUpdate() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		ExpenseCategory otherExpenseCategory = new ExpenseCategory();
		otherExpenseCategory.setName("Другая категория");
		otherExpenseCategory.setDescription("Описание другой категории");
		expenseCategoryRepo.save(otherExpenseCategory);

		purchase.setExpense("Измененная покупка");
		purchase.setDescription("Описание измененной покупки");
		purchase.setCategory(otherExpenseCategory);

		/*Act*/
		purchaseService.updatePurchase(purchase);

		/*Assert*/
		Purchase currentPurchase = purchaseRepo.findById(purchase.getId()).orElse(null);
		Assertions.assertNotNull(currentPurchase);
		Assertions.assertEquals(currentPurchase.getId(), purchase.getId());
		Assertions.assertEquals(currentPurchase.getExpense(), purchase.getExpense());
		Assertions.assertEquals(currentPurchase.getDescription(), purchase.getDescription());
		Assertions.assertNotNull(currentPurchase.getCategory());
		Assertions.assertEquals(currentPurchase.getCategory().getName(), purchase.getCategory().getName());
		Assertions.assertEquals(currentPurchase.getCategory().getDescription(), purchase.getCategory().getDescription());
	}

	@Test
	public void checkPurchaseDelete() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		Purchase currentPurchase = purchaseRepo.findById(purchase.getId()).orElse(null);
		Assertions.assertNotNull(currentPurchase);

		/*Act*/
		purchaseService.deletePurchase(currentPurchase.getId());

		/*Assert*/
		List<Purchase> purchaseList = purchaseRepo.findAll();
		Assertions.assertTrue(purchaseList.isEmpty());
	}

	@Test
	public void checkPurchaseGetById() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		Purchase purchase = new Purchase();
		purchase.setExpense("Новая покупка");
		purchase.setDescription("Описание новой покупки");
		purchase.setCategory(expenseCategory);
		purchaseRepo.save(purchase);

		/*Act*/
		Purchase currentPurchase = purchaseService.getPurchase(purchase.getId());

		/*Assert*/
		Assertions.assertNotNull(currentPurchase);
		Assertions.assertEquals(currentPurchase.getId(), purchase.getId());
		Assertions.assertEquals(currentPurchase.getExpense(), purchase.getExpense());
		Assertions.assertEquals(currentPurchase.getDescription(), purchase.getDescription());
		Assertions.assertNotNull(currentPurchase.getCategory());
		Assertions.assertEquals(currentPurchase.getCategory().getName(), purchase.getCategory().getName());
		Assertions.assertEquals(currentPurchase.getCategory().getDescription(), purchase.getCategory().getDescription());
	}

	@Test
	public void checkPurchaseGetAll() {
		/*Arrange*/
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setName("Новая категория");
		expenseCategory.setDescription("Описание новой категории");
		expenseCategoryRepo.save(expenseCategory);

		List<Purchase> purchaseList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Purchase purchase = new Purchase();
			purchase.setExpense("Новая покупка " + i);
			purchase.setDescription("Описание новой покупки " + i);
			purchase.setCategory(expenseCategory);
			purchaseList.add(purchase);
			purchaseRepo.save(purchase);
		}

		/*Act*/
		List<Purchase> currentPurchaseList = purchaseService.getAllPurchase();

		/*Assert*/
		Assertions.assertFalse(currentPurchaseList.isEmpty());
		for (int i = 0; i < 10; i++) {
			Assertions.assertNotNull(currentPurchaseList.get(i));
			Assertions.assertEquals(currentPurchaseList.get(i).getExpense(), purchaseList.get(i).getExpense());
			Assertions.assertEquals(currentPurchaseList.get(i).getDescription(), purchaseList.get(i).getDescription());
			Assertions.assertNotNull(currentPurchaseList.get(i).getCategory());
			Assertions.assertEquals(currentPurchaseList.get(i).getCategory().getName(),
									purchaseList.get(i).getCategory().getName());
			Assertions.assertEquals(currentPurchaseList.get(i).getCategory().getDescription(),
									purchaseList.get(i).getCategory().getDescription());
		}
	}

}
