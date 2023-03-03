package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.BudgetDto;
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
    public List<BudgetDto> getAllBudget() {
        return budgetService.getAllBudgetDto();
    }

}
