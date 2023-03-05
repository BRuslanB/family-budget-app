package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(value = "dates/{date1}/{date2}")
    public List<BudgetDto> getAllBudgetBetweenDate(@PathVariable(name = "date1") LocalDate dateFrom,
                                                   @PathVariable(name = "date2") LocalDate dateTo) {
        return budgetService.getAllBudgetBetweenDateDto(dateFrom, dateTo);
    }

}
