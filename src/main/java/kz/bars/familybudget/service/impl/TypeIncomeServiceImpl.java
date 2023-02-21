package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.TypeIncomeDto;
import kz.bars.familybudget.mapper.TypeIncomeMapper;
import kz.bars.familybudget.model.TypeIncome;
import kz.bars.familybudget.repository.TypeIncomeRepo;
import kz.bars.familybudget.service.TypeIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeIncomeServiceImpl implements TypeIncomeService {

    private final TypeIncomeRepo typeIncomeRepo;
    private final TypeIncomeMapper typeIncomeMapper;


    @Override
    public List<TypeIncome> getAllTypeIncome() {
        return typeIncomeRepo.findAll();
    }

    @Override
    public TypeIncome getTypeIncome(Long id) {
        return typeIncomeRepo.findById(id).orElseThrow();
    }

    @Override
    public TypeIncome addTypeIncome(TypeIncome typeIncome) {
        return typeIncomeRepo.save(typeIncome);
    }

    @Override
    public TypeIncome updateTypeIncome(TypeIncome typeIncome) {
        return typeIncomeRepo.save(typeIncome);
    }

    @Override
    public void deleteTypeIncome(Long id) {
        typeIncomeRepo.deleteById(id);
    }

    @Override
    public List<TypeIncomeDto> getAllTypeIncomeDto() {
        return typeIncomeMapper.toDtoList(typeIncomeRepo.findAll());
    }

    @Override
    public TypeIncomeDto getTypeIncomeDto(Long id) {
        return typeIncomeMapper.toDto(typeIncomeRepo.findById(id).orElseThrow());
    }

    @Override
    public TypeIncomeDto addTypeIncomeDto(TypeIncomeDto typeIncomeDto) {
        return typeIncomeMapper.toDto(typeIncomeRepo.save(typeIncomeMapper.toEntity(typeIncomeDto)));
    }

    @Override
    public TypeIncomeDto updateTypeIncomeDto(TypeIncomeDto typeIncomeDto) {
        return typeIncomeMapper.toDto(typeIncomeRepo.save(typeIncomeMapper.toEntity(typeIncomeDto)));
    }
}
