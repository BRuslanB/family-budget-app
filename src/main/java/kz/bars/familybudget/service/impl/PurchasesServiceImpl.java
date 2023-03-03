package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.mapper.TypeExpenseMapper;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.repository.PurchaseRepo;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasesServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;
    private final TypeExpenseMapper typeExpenseMapper;

    @Override
    public PurchaseDto toDto(Purchase purchase) {

        if (purchase == null) {
            return null;
        }

        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setTypeExpense(typeExpenseMapper.toDto(purchase.getTypeExpense()));

        return purchaseDto;
    }

    @Override
    public List<PurchaseDto> getAllPurchaseDto() {

        List<Purchase> purchaseList;
        purchaseList = purchaseRepo.findAll();

        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        PurchaseDto purchaseDto;
        for (Purchase purchase : purchaseList) {
            purchaseDto = new PurchaseDto();
            purchaseDto.setId(purchase.getId());
            purchaseDto.setTypeExpense(typeExpenseMapper.toDto(purchase.getTypeExpense()));
            // Count Value
            var sum = 0.0;
            for (Check check : purchase.getChecks()) {
                sum += check.getValue();
            }
            purchaseDto.setSumValue(Math.round(sum * 100.0) / 100.0);
            //Add to purchaseDtoList
            purchaseDtoList.add(purchaseDto);
        }
        return purchaseDtoList;
    }

}
