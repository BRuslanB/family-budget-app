package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.IncomeDto;
import kz.bars.familybudget.model.Income;
import kz.bars.familybudget.model.Check;
import kz.bars.familybudget.repository.IncomeRepo;
import kz.bars.familybudget.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    final IncomeRepo incomeRepo;

    @Override
    public List<Income> getAllIncome() {
        return incomeRepo.findAll();
    }

    @Override
    public Income getIncome(Long id) {
        return incomeRepo.findById(id).orElseThrow();
    }

    @Override
    public Income addIncome(Income income) {
        return incomeRepo.save(income);
    }

    @Override
    public Income updateIncome(Income income) {
        return incomeRepo.save(income);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepo.deleteById(id);
    }

    @Override
    public IncomeDto toDto(Income income) {
        if (income == null) {
            return null;
        }
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setId(income.getId());
        incomeDto.setName(income.getName());
        incomeDto.setDescription(income.getDescription());

        return incomeDto;
    }

    @Override
    public List<IncomeDto> getAllIncomeDto() {
        List<Income> incomeList;
        incomeList = incomeRepo.findAll();

        List<IncomeDto> incomeDtoList = new ArrayList<>();
        IncomeDto incomeDto;
        for (Income income : incomeList) {
            incomeDto = new IncomeDto();
            incomeDto.setId(income.getId());
            incomeDto.setName(income.getName());
            incomeDto.setDescription(income.getDescription());
            //Count Value
            var sum = 0.0;
            for (Check check : income.getChecks()) {
                sum += check.getVal();
            }
            incomeDto.setSumVal(Math.round(sum * 100.0) / 100.0);
            //Add to budgetDtoList
            incomeDtoList.add(incomeDto);
        }
        return incomeDtoList;
    }

    @Override
    public List<IncomeDto> getAllIncomeBetweenDateDto(LocalDate dateFrom, LocalDate dateTo) {
        List<Income> incomeList;
        incomeList = incomeRepo.findAllByChecksBetweenDateOrderByDate(dateFrom, dateTo);

        List<IncomeDto> incomeDtoList = new ArrayList<>();
        IncomeDto incomeDto;
        for (Income income : incomeList) {
            incomeDto = new IncomeDto();
            incomeDto.setId(income.getId());
            incomeDto.setName(income.getName());
            incomeDto.setDescription(income.getDescription());
            //Count Value
            var sum = 0.0;
            for (Check check : income.getChecks()) {
                if (check.getDate().compareTo(dateFrom) >= 0 && check.getDate().compareTo(dateTo) <= 0) {
                    sum += check.getVal();
                }
            }
            incomeDto.setSumVal(Math.round(sum * 100.0) / 100.0);
            //Add to budgetDtoList
            incomeDtoList.add(incomeDto);
        }
        return incomeDtoList;
    }

}
