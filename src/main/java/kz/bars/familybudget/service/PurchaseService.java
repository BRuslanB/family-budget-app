package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.model.ExpenseCategory;
import kz.bars.familybudget.model.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    public List<Purchase> getAllPurchase();
    public Purchase getPurchase(Long id);
    public Purchase addPurchase(Purchase purchase);
    public Purchase updatePurchase(Purchase purchase);
    public void deletePurchase(Long id);
    public PurchaseDto toDto(Purchase purchase);
    public List<PurchaseDto> getAllPurchaseDto();
    public List<PurchaseDto> getAllPurchaseBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
