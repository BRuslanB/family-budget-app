package kz.bars.familybudget.service;

import kz.bars.familybudget.dto.TypeExpenseDto;
import kz.bars.familybudget.model.TypeExpense;

import java.util.List;

public interface TypeExpenseService {

    public List<TypeExpense> getAllTypeExpense();
    public TypeExpense getTypeExpense(Long id);
    public TypeExpense addTypeExpense(TypeExpense typeExpense);
    public TypeExpense updateTypeExpense(TypeExpense typeExpense);
    public void deleteTypeExpense(Long id);
    public List<TypeExpenseDto> getAllTypeExpenseDto();
    public TypeExpenseDto getTypeExpenseDto(Long id);
    public TypeExpenseDto addTypeExpenseDto(TypeExpenseDto typeExpenseDto);
    public TypeExpenseDto updateTypeExpenseDto(TypeExpenseDto typeExpenseDto);

}
