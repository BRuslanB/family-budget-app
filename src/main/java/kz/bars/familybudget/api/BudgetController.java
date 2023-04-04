package kz.bars.familybudget.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Доходы", description = "Все методы для получения списка доходов")
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    @Operation(description = "Получение списка доходов")
    public List<BudgetDto> getAllBudget() {
        return budgetService.getAllBudgetDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    @Operation(description = "Получение списка доходов за период с.. по..")
    public List<BudgetDto> getAllBudgetBetweenDate(@Parameter(description = "дата начала")
                                                   @PathVariable(name = "date1") LocalDate dateFrom,
                                                   @Parameter(description = "дата окончания")
                                                   @PathVariable(name = "date2") LocalDate dateTo) {
        return budgetService.getAllBudgetBetweenDateDto(dateFrom, dateTo);
    }

}
