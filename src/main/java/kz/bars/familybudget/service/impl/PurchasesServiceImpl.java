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
//    private final PurchaseMapper purchaseMapper;
    private final TypeExpenseMapper typeExpenseMapper;

//    @Override
//    public List<Purchase> getAllPurchase() {
//        return purchaseRepo.findAll();
//    }
//
//    @Override
//    public Purchase getPurchase(Long id) {
//        return purchaseRepo.findById(id).orElseThrow();
//    }
//
//    @Override
//    public Purchase addPurchase(Purchase purchase) {
//        return purchaseRepo.save(purchase);
//    }
//
//    @Override
//    public Purchase updatePurchase(Purchase purchase) {
//        return purchaseRepo.save(purchase);
//    }
//
//    @Override
//    public void deletePurchase(Long id) {
//        purchaseRepo.deleteById(id);
//    }

    @Override
    public List<PurchaseDto> getAllPurchaseDto() {
//        return purchaseMapper.toDtoList(purchaseRepo.findAll());
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

//    @Override
//    public PurchaseDto getPurchaseDto(Long id) {
//        return purchaseMapper.toDto(purchaseRepo.findById(id).orElseThrow());
//    }
//
//    @Override
//    public PurchaseDto addPurchaseDto(PurchaseDto purchaseDto) {
//        return purchaseMapper.toDto(purchaseRepo.save(purchaseMapper.toEntity(purchaseDto)));
//    }
//
//    @Override
//    public PurchaseDto updatePurchaseDto(PurchaseDto purchaseDto) {
//        return purchaseMapper.toDto(purchaseRepo.save(purchaseMapper.toEntity(purchaseDto)));
//    }
}
