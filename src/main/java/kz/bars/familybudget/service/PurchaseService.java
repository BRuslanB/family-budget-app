package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.model.Purchase;

import java.util.List;

public interface PurchaseService {
    public PurchaseDto toDto(Purchase purchase);
    public List<PurchaseDto> getAllPurchaseDto();

}
