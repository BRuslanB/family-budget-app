package kz.bars.familybudget.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.service.CheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/checks")
@CrossOrigin
@Log4j2
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "family-budget-api")
@Tag(name = "Check", description = "All methods for getting a list of Check")
public class CheckController {

    private final CheckService checkService;

    @GetMapping(value = "{id}")
    @Operation(description = "Getting a Check..")
    public CheckDto getCheck(@Parameter(description = "'check' id")
                             @PathVariable(name = "id") Long id) {
        log.debug("!Getting a Check, id={}", id);
        return checkService.getCheckDto(id);
    }

    @PostMapping
    @Operation(description = "Check added")
    public CheckDto addCheck(@RequestBody CheckDto checkDto) {
        log.debug("!Check added");
        return checkService.addCheckDto(checkDto);
    }

    @PutMapping
    @Operation(description = "Check updated")
    public CheckDto updateCheck(@RequestBody CheckDto checkDto) {
        log.debug("!Check updated");
        return checkService.updateCheckDto(checkDto);
    }

    @DeleteMapping(value = "{id}")
    @Operation(description = "Check.. removed")
    public void deleteCheck(@Parameter(description = "'check' id")
                            @PathVariable(name = "id") Long id) {
        log.error("!Check removed, id={}", id);
        checkService.deleteCheckDto(id);
    }

    @GetMapping()
    @Operation(description = "Getting a list of Check")
    public List<CheckDto> getAllCheck() {
        log.debug("!Getting a list of Check");
        return checkService.getAllCheckDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    @Operation(description = "Getting a list of Check for the period from.. to..")
    public List<CheckDto> getAllCheckBetweenDate(@Parameter(description = "date 'from'")
                                                 @PathVariable(name = "date1") LocalDate dateFrom,
                                                 @Parameter(description = "date 'to'")
                                                 @PathVariable(name = "date2") LocalDate dateTo) {
        log.debug("!Getting a list of Check for the period from {} to {}", dateFrom, dateTo);
        return checkService.getAllCheckBetweenDateDto(dateFrom, dateTo);
    }

    @GetMapping(value = "incomes/{id}")
    @Operation(description = "Getting a list of Checks for a given Income..")
    public List<CheckDto> getAllCheckByBudgetId(@Parameter(description = "'income' id")
                                                @PathVariable(name = "id") Long id) {
        log.debug("!Getting a list of Checks for a given Income, id={}", id);
        return checkService.getAllCheckByBudgetIdDto(id);
    }

    @GetMapping(value = "incomes/{id}/dates/{date1}/{date2}")
    @Operation(description = "Getting a list of Checks for a given Income.. for the period from.. to..")
    public List<CheckDto> getAllCheckByBudgetBetweenDate(@Parameter(description = "'income' id")
                                                         @PathVariable(name = "id") Long id,
                                                         @Parameter(description = "date 'from'")
                                                         @PathVariable(name = "date1") LocalDate dateFrom,
                                                         @Parameter(description = "date 'to'")
                                                         @PathVariable(name = "date2") LocalDate dateTo) {
        log.debug("!Getting a list of Checks for a given Income, id={} from {} to {}", id, dateFrom, dateTo);
        return checkService.getAllCheckByBudgetBetweenDateDto(id, dateFrom, dateTo);
    }

    @GetMapping(value = "expenses/{id}")
    @Operation(description = "Getting a list of Checks for a given Expense..")
    public List<CheckDto> getAllCheckByPurchaseId(@Parameter(description = "'expense' id")
                                                  @PathVariable(name = "id") Long id) {
        log.debug("!Getting a list of Checks for a given Purchase, id={}", id);
        return checkService.getAllCheckByPurchaseIdDto(id);
    }

    @GetMapping(value = "expenses/{id}/dates/{date1}/{date2}")
    @Operation(description = "Getting a list of Checks for a given Expense.. for the period from.. to..")
    public List<CheckDto> getAllCheckByPurchaseBetweenDate(@Parameter(description = "'expense' id")
                                                           @PathVariable(name = "id") Long id,
                                                           @Parameter(description = "date 'from'")
                                                           @PathVariable(name = "date1") LocalDate dateFrom,
                                                           @Parameter(description = "date 'to'")
                                                           @PathVariable(name = "date2") LocalDate dateTo) {
        log.debug("!Getting a list of Checks for a given Purchase, id={} from {} to {}",id, dateFrom, dateTo);
        return checkService.getAllCheckByPurchaseBetweenDateDto(id, dateFrom, dateTo);
    }

}
