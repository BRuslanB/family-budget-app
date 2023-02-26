package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.model.Budget;
import kz.bars.familybudget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/budget")
@CrossOrigin
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    public List<BudgetDto> getAllBudgetDto() {
        return budgetService.getAllBudgetDto();
    }

//    @GetMapping(value = "{id}")
//    public BudgetDto getBudget(@PathVariable(name = "id") Long id) {
//        return budgetService.getBudgetDto(id);
//    }

//    @PostMapping
//    public BudgetDto addBudget(@RequestBody BudgetDto budgetDto) {
//        return budgetService.addBudgetDto(budgetDto);
//    }

//    @PutMapping
//    public BudgetDto updateBudget(@RequestBody BudgetDto budgetDto) {
//        return budgetService.updateBudgetDto(budgetDto);
//    }

//    @DeleteMapping(value = "{id}")
//    public void deleteBudget(@PathVariable(name = "id") Long id) {
//        budgetService.deleteBudget(id);
//    }

}
