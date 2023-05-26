package kz.bars.familybudget.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bars.familybudget.dto.IncomeDto;
import kz.bars.familybudget.service.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/incomes")
@CrossOrigin
@Log4j2
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "family-budget")
@Tag(name = "Income", description = "All methods for getting a list of Incomes")
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping
    @Operation(description = "Getting a list of Incomes")
    public List<IncomeDto> getAllIncome() {
        log.debug("!Getting a list of Incomes");
        return incomeService.getAllIncomeDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    @Operation(description = "Getting a list of Incomes for the period from.. to..")
    public List<IncomeDto> getAllIncomeBetweenDate(@Parameter(description = "date 'from'")
                                                   @PathVariable(name = "date1") LocalDate dateFrom,
                                                   @Parameter(description = "date 'to'")
                                                   @PathVariable(name = "date2") LocalDate dateTo) {
        log.debug("!Getting a list of Incomes for the period from {} to {}",dateFrom, dateTo);
        return incomeService.getAllIncomeDtoBetweenDate(dateFrom, dateTo);
    }

}
