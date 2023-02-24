package kz.bars.familybudget.api;

import kz.bars.familybudget.model.TypeExpense;
import kz.bars.familybudget.service.TypeExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/expenses")
@CrossOrigin
public class TypeExpenseController {

    private final TypeExpenseService typeExpenseService;

    @GetMapping
    public List<TypeExpense> getAllTypeExpense() {
        return typeExpenseService.getAllTypeExpense();
    }

    @GetMapping(value = "{id}")
    public TypeExpense getTypeExpense(@PathVariable(name = "id") Long id) {
        return typeExpenseService.getTypeExpense(id);
    }

    @PostMapping
    public TypeExpense addTypeExpense(@RequestBody TypeExpense typeExpense) {
        return typeExpenseService.addTypeExpense(typeExpense);
    }

    @PutMapping
    public TypeExpense updateTypeExpense(@RequestBody TypeExpense typeExpense) {
        return typeExpenseService.updateTypeExpense(typeExpense);
    }

    @DeleteMapping(value = "{id}")
    public void deleteTypeExpense(@PathVariable(name = "id") Long id) {
        typeExpenseService.deleteTypeExpense(id);
    }

}
