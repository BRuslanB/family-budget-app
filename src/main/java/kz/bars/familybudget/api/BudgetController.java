package kz.bars.familybudget.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bars.familybudget.dto.BudgetDto;
import kz.bars.familybudget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/budget")
@CrossOrigin
@Log4j2
@SecurityRequirement(name = "javainuseapi")
@Tag(name = "Income", description = "All methods for getting a list of Income")
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Getting a list of income")
    public List<BudgetDto> getAllBudget() {
        log.info("!Getting a list of Income");
        return budgetService.getAllBudgetDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Getting a list of Income for the period from.. to..")
    public List<BudgetDto> getAllBudgetBetweenDate(@Parameter(description = "date from")
                                                   @PathVariable(name = "date1") LocalDate dateFrom,
                                                   @Parameter(description = "date to")
                                                   @PathVariable(name = "date2") LocalDate dateTo) {
        log.info("!Getting a list of Income for the period " + "from " + dateFrom + " to "+ dateTo);
        return budgetService.getAllBudgetBetweenDateDto(dateFrom, dateTo);
    }

}
