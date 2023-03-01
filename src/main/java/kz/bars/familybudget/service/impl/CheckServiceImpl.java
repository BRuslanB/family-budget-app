package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.mapper.CheckMapper;
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
import org.springframework.stereotype.Service;

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

//    private final CheckMapper checkMapper;

//    @Override
//    public List<Check> getAllCheck() {
//        return checkRepo.findAll();
//    }
//
    @Override
    public CheckDto getCheckDto(Long id) {
        Check check = checkRepo.findById(id).orElse(null);
        CheckDto checkDto = new CheckDto();

        if (check != null) {
            checkDto.setId(check.getId());
            checkDto.setValue(check.getValue());
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

        check.setValue(checkDto.getValue());
        check.setDate(checkDto.getDate());
        check.setNote(checkDto.getNote());
        try {
            if (checkDto.getBudget() != null) {
                Budget budget = budgetRepo.findById(checkDto.getBudget().getId()).orElse(null);
                if (budget != null) {
                    check.setBudget(budget);
                }
            }
        } catch(Exception ex) {
//            check.setBudget(null);
        }
        try {
            if (checkDto.getPurchase() != null) {
//            if (checkDto.getPurchaseId() != null) {
//                Purchase purchase = purchaseRepo.findById(checkDto.getPurchaseId()).orElse(null);
                Purchase purchase = purchaseRepo.findById(checkDto.getPurchase().getId()).orElse(null);
                if (purchase != null) {
                    check.setPurchase(purchase);
                }
            }
        } catch(Exception ex) {
//            check.setPurchase(null);
        }
       checkRepo.save(check);
       return checkDto;
    }

    @Override
    public CheckDto updateCheckDto(CheckDto checkDto) {
        Check check = checkRepo.findById(checkDto.getId()).orElse(null);

        if (check != null) {
            check.setValue(checkDto.getValue());
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
    public void deleteCheckDto(Long id) {
        checkRepo.deleteById(id);
    }

    @Override
    public List<CheckDto> getAllCheckDto() {
//        return checkMapper.toDtoList(checkRepo.findAll());
        List<Check> checkList;
        checkList = checkRepo.findAll();

        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;
        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setValue(check.getValue());
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
    public List<CheckDto> getAllCheckByBudgetIdDto(Long id) {
        List<Check> checkList = checkRepo.findAllByBudgetId(id);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setValue(check.getValue());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setBudget(budgetService.toDto(check.getBudget()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

    @Override
    public List<CheckDto> getAllCheckByPurchaseIdDto(Long id) {
        List<Check> checkList = checkRepo.findAllByPurchaseId(id);
        List<CheckDto> checkDtoList = new ArrayList<>();
        CheckDto checkDto;

        for (Check check : checkList) {
            checkDto = new CheckDto();
            checkDto.setId(check.getId());
            checkDto.setValue(check.getValue());
            checkDto.setDate(check.getDate());
            checkDto.setNote(check.getNote());
            checkDto.setPurchase(purchaseService.toDto(check.getPurchase()));
            //Add to checkDtoList
            checkDtoList.add(checkDto);
        }
        return checkDtoList;
    }

//    @Override
//    public CheckDto getCheckDto(Long id) {
//        return checkMapper.toDto(checkRepo.findById(id).orElseThrow());
//    }
//
//    @Override
//    public CheckDto addCheckDto(CheckDto checkDto) {
//        return checkMapper.toDto(checkRepo.save(checkMapper.toEntity(checkDto)));
//    }
//
//    @Override
//    public CheckDto updateCheckDto(CheckDto checkDto) {
//        return checkMapper.toDto(checkRepo.save(checkMapper.toEntity(checkDto)));
//    }

}
