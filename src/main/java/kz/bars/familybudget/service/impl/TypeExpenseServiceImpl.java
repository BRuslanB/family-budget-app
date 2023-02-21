package kz.bars.familybudget.service.impl;

import kz.bars.familybudget.dto.TypeExpenseDto;
import kz.bars.familybudget.mapper.TypeExpenseMapper;
import kz.bars.familybudget.model.TypeExpense;
import kz.bars.familybudget.repository.TypeExpenseRepo;
import kz.bars.familybudget.service.TypeExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeExpenseServiceImpl implements TypeExpenseService {

    private final TypeExpenseRepo typeExpenseRepo;
    private final TypeExpenseMapper typeExpenseMapper;


    @Override
    public List<TypeExpense> getAllTypeExpense() {
        return typeExpenseRepo.findAll();
    }

    @Override
    public TypeExpense getTypeExpense(Long id) {
        return typeExpenseRepo.findById(id).orElseThrow();
    }

    @Override
    public TypeExpense addTypeExpense(TypeExpense typeExpense) {
        return typeExpenseRepo.save(typeExpense);
    }

    @Override
    public TypeExpense updateTypeExpense(TypeExpense typeExpense) {
        return typeExpenseRepo.save(typeExpense);
    }

    @Override
    public void deleteTypeExpense(Long id) {
        typeExpenseRepo.deleteById(id);
    }

    @Override
    public List<TypeExpenseDto> getAllTypeExpenseDto() {
        return typeExpenseMapper.toDtoList(typeExpenseRepo.findAll());
    }

    @Override
    public TypeExpenseDto getTypeExpenseDto(Long id) {
        return typeExpenseMapper.toDto(typeExpenseRepo.findById(id).orElseThrow());
    }

    @Override
    public TypeExpenseDto addTypeExpenseDto(TypeExpenseDto typeExpenseDto) {
        return typeExpenseMapper.toDto(typeExpenseRepo.save(typeExpenseMapper.toEntity(typeExpenseDto)));
    }

    @Override
    public TypeExpenseDto updateTypeExpenseDto(TypeExpenseDto typeExpenseDto) {
        return typeExpenseMapper.toDto(typeExpenseRepo.save(typeExpenseMapper.toEntity(typeExpenseDto)));
    }
}
