package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.repository.BudgetRepo;
import kz.bars.familybudget.repository.CheckRepo;
import kz.bars.familybudget.repository.PurchaseRepo;
import kz.bars.familybudget.service.BudgetService;
import kz.bars.familybudget.service.CheckService;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final CheckRepo checkRepo;
    private final BudgetRepo budgetRepo;
    private final BudgetService budgetService;
    private final PurchaseRepo purchaseRepo;
    private final PurchaseService purchaseService;

    @Override
    public CheckDto getCheckDto(BigInteger id) {

        Check check = checkRepo.findById(id).orElse(null);
        CheckDto checkDto = new CheckDto();

        if (check != null) {
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setBudget(budgetService.toDto(check.getBudget()));
            checkDto.setPurchase(purchaseService.toDto(check.getPurchase()));
        }
        return checkDto;
    }

    @Override
    public CheckDto addCheckDto(CheckDto checkDto) {

        Check check = new Check();

        check.setVal(checkDto.getVal());
        check.setDate(checkDto.getDate());
        check.setNote(checkDto.getNote());
        try {
            if (checkDto.getBudget() != null) {
                budgetRepo.findById(checkDto.getBudget().getId()).ifPresent(check::setBudget);
            }
        } catch(Exception ex) {
//            check.setBudget(null);
        }
        try {
            if (checkDto.getPurchase() != null) {
                purchaseRepo.findById(checkDto.getPurchase().getId()).ifPresent(check::setPurchase);
            }
        } catch(Exception ex) {
//            check.setPurchase(null);
        }
       checkRepo.save(check);
       return checkDto;
    }

    @Override
    public CheckDto updateCheckDto(CheckDto checkDto) {
        Check check;
        try {
            check = checkRepo.findById(checkDto.getId()).orElseThrow();
        } catch (Exception ex) {
            check = null;
        }

        if (check != null) {
            check.setVal(checkDto.getVal());
            check.setDate(checkDto.getDate());
            check.setNote(checkDto.getNote());
            try {
                Budget budget = budgetRepo.findById(checkDto.getBudget().getId()).orElseThrow();
                check.setBudget(budget);
            } catch (Exception ex) {
//                check.setBudget(null);
            }
            try {
                Purchase purchase = purchaseRepo.findById(checkDto.getPurchase().getId()).orElseThrow();
                check.setPurchase(purchase);
            } catch (Exception ex) {
//                check.setPurchase(null);
            }
            checkRepo.save(check);
        }
        return checkDto;
    }

    @Override
    public void deleteCheckDto(BigInteger id) {
        checkRepo.deleteById(id);
    }

    @Override
    public List<CheckDto> getAllCheckDto() {
        List<Check> checkList;
        checkList = checkRepo.findAll(Sort.by(Sort.Order.by("date")));

        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;
        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setBudget(budgetService.toDto(check.getBudget()));
            checkDto.setPurchase(purchaseService.toDto(check.getPurchase()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckBetweenDateDto(LocalDate dateFrom, LocalDate dateTo) {
        List<Check> checkList;
        checkList = checkRepo.findAllByDateBetweenOrderByDate(dateFrom, dateTo);

        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;
        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setBudget(budgetService.toDto(check.getBudget()));
            checkDto.setPurchase(purchaseService.toDto(check.getPurchase()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByBudgetIdDto(BigInteger id) {

        List<Check> checkList = checkRepo.findAllByBudgetIdOrderByDate(id);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setBudget(budgetService.toDto(check.getBudget()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByBudgetBetweenDateDto(BigInteger id, LocalDate dateFrom, LocalDate dateTo) {

        List<Check> checkList = checkRepo.findAllByBudgetIdAndDateBetweenOrderByDate(id, dateFrom, dateTo);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setBudget(budgetService.toDto(check.getBudget()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByPurchaseIdDto(BigInteger id) {

        List<Check> checkList = checkRepo.findAllByPurchaseIdOrderByDate(id);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setPurchase(purchaseService.toDto(check.getPurchase()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByPurchaseBetweenDateDto(BigInteger id, LocalDate dateFrom, LocalDate dateTo) {

        List<Check> checkList = checkRepo.findAllByPurchaseIdAndDateBetweenOrderByDate(id, dateFrom, dateTo);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setVal(check.getVal());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setPurchase(purchaseService.toDto(check.getPurchase()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

}
