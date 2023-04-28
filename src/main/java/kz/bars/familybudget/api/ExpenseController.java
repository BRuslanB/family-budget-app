package kz.bars.familybudget.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bars.familybudget.dto.ExpenseDto;
import kz.bars.familybudget.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/expenses")
@CrossOrigin
@Log4j2
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "family-budget-api")
@Tag(name = "Expense", description = "All methods for getting a list of Expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    @Operation(description = "Getting a list of Expense")
    public List<ExpenseDto> getAllExpense() {
        log.debug("!Getting a list of Expense");
        return expenseService.getAllExpenseDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    @Operation(description = "Getting a list of Expense for the period from.. to..")
    public List<ExpenseDto> getAllExpenseBetweenDate(@Parameter(description = "date 'from'")
                                                       @PathVariable(name = "date1") LocalDate dateFrom,
                                                      @Parameter(description = "date 'to'")
                                                       @PathVariable(name = "date2") LocalDate dateTo) {
        log.debug("!Getting a list of Expense for the period from {} to {}", dateFrom, dateTo);
        return expenseService.getAllExpenseBetweenDateDto(dateFrom, dateTo);
    }

}