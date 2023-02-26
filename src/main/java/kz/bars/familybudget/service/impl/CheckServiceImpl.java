package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.mapper.CheckMapper;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.repository.CheckRepo;
import kz.bars.familybudget.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final CheckRepo checkRepo;
//    private final CheckMapper checkMapper;

//    @Override
//    public List<Check> getAllCheck() {
//        return checkRepo.findAll();
//    }
//
//    @Override
//    public Check getCheck(Long id) {
//        return checkRepo.findById(id).orElseThrow();
//    }
//
//    @Override
//    public Check addCheck(Check check) {
//        return checkRepo.save(check);
//    }
//
//    @Override
//    public Check updateCheck(Check check) {
//        return checkRepo.save(check);
//    }
//
//    @Override
//    public void deleteCheck(Long id) {
//        checkRepo.deleteById(id);
//    }

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
            try {
                checkDto.setBudgetId(check.getBudget().getId());
            } catch(NullPointerException ex) {
                checkDto.setBudgetId(-1L);
            }
            try {
                checkDto.setPurchaseId(check.getPurchase().getId());
            } catch(NullPointerException ex) {
                checkDto.setPurchaseId(-1L);
            }
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
