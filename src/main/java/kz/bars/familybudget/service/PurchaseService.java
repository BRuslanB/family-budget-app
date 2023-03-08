package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.model.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    List<Purchase> getAllPurchase();
    Purchase getPurchase(Long id);
    Purchase addPurchase(Purchase purchase);
    Purchase updatePurchase(Purchase purchase);
    void deletePurchase(Long id);
    PurchaseDto toDto(Purchase purchase);
    List<PurchaseDto> getAllPurchaseDto();
    List<PurchaseDto> getAllPurchaseBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
