package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.mapper.ExpenseCategoryMapper;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.repository.PurchaseRepo;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasesServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Override
    public List<Purchase> getAllPurchase() {
        return purchaseRepo.findAll();
    }

    @Override
    public Purchase getPurchase(Long id) {
        return purchaseRepo.findById(id).orElseThrow();
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepo.save(purchase);
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return purchaseRepo.save(purchase);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepo.deleteById(id);
    }

    @Override
    public PurchaseDto toDto(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setExpense(purchase.getExpense());
        purchaseDto.setDescription(purchase.getDescription());
        purchaseDto.setCategory(expenseCategoryMapper.toDto(purchase.getCategory()));

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
            purchaseDto.setExpense(purchase.getExpense());
            purchaseDto.setDescription(purchase.getDescription());
            purchaseDto.setCategory(expenseCategoryMapper.toDto(purchase.getCategory()));
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

    @Override
    public List<PurchaseDto> getAllPurchaseBetweenDateDto(LocalDate dateFrom, LocalDate dateTo) {
        List<Purchase> purchaseList;
        purchaseList = purchaseRepo.findAllByChecksBetweenDateOrderByDate(dateFrom, dateTo);

        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        PurchaseDto purchaseDto;
        for (Purchase purchase : purchaseList) {
            purchaseDto = new PurchaseDto();
            purchaseDto.setId(purchase.getId());
            purchaseDto.setExpense(purchase.getExpense());
            purchaseDto.setDescription(purchase.getDescription());
            purchaseDto.setCategory(expenseCategoryMapper.toDto(purchase.getCategory()));
            // Count Value
            var sum = 0.0;
            for (Check check : purchase.getChecks()) {
                if (check.getDate().compareTo(dateFrom) >= 0 && check.getDate().compareTo(dateTo) <= 0) {
                    sum += check.getValue();
                }
            }
            purchaseDto.setSumValue(Math.round(sum * 100.0) / 100.0);
            //Add to purchaseDtoList
            purchaseDtoList.add(purchaseDto);
        }
        return purchaseDtoList;
    }

}
