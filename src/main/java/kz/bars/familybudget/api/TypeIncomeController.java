package kz.bars.familybudget.api;

import kz.bars.familybudget.model.TypeIncome;
import kz.bars.familybudget.service.TypeIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/income")
@CrossOrigin
public class TypeIncomeController {

    private final TypeIncomeService typeIncomeService;

    @GetMapping
    public List<TypeIncome> getAllTypeIncome() {
        return typeIncomeService.getAllTypeIncome();
    }

    @GetMapping(value = "{id}")
    public TypeIncome getTypeIncome(@PathVariable(name = "id") Long id) {
        return typeIncomeService.getTypeIncome(id);
    }

    @PostMapping
    public TypeIncome addBudget(@RequestBody TypeIncome typeIncome) {
        return typeIncomeService.addTypeIncome(typeIncome);
    }

    @PutMapping
    public TypeIncome updateTypeIncome(@RequestBody TypeIncome typeIncome) {
        return typeIncomeService.updateTypeIncome(typeIncome);
    }

    @DeleteMapping(value = "{id}")
    public void deleteBudget(@PathVariable(name = "id") Long id) {
        typeIncomeService.deleteTypeIncome(id);
    }

}
