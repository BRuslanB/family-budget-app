package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.model.Purchase;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    List<Purchase> getAllPurchase();
    Purchase getPurchase(BigInteger id);
    Purchase addPurchase(Purchase purchase);
    Purchase updatePurchase(Purchase purchase);
    void deletePurchase(BigInteger id);
    PurchaseDto toDto(Purchase purchase);
    List<PurchaseDto> getAllPurchaseDto();
    List<PurchaseDto> getAllPurchaseBetweenDateDto(LocalDate dateFrom, LocalDate dateTo);

}
