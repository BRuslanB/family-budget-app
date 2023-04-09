package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.service.CheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/checks")
@CrossOrigin
@Log4j2
public class CheckController {

    private final CheckService checkService;

    @GetMapping(value = "{id}")
    public CheckDto getCheck(@PathVariable(name = "id") BigInteger id) {
        log.info("!Getting a Check, " + "id=" + id);
        return checkService.getCheckDto(id);
    }

    @PostMapping
    public CheckDto addCheck(@RequestBody CheckDto checkDto) {
        log.info("!Check added");
        return checkService.addCheckDto(checkDto);
    }

    @PutMapping
    public CheckDto updateCheck(@RequestBody CheckDto checkDto) {
        log.info("!Check updated");
        return checkService.updateCheckDto(checkDto);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCheck(@PathVariable(name = "id") BigInteger id) {
        log.info("!Check removed, " + "id=" + id);
        checkService.deleteCheckDto(id);
    }

    @GetMapping()
    public List<CheckDto> getAllCheck() {
        log.info("!Getting a list of Check");
        return checkService.getAllCheckDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    public List<CheckDto> getAllCheckBetweenDate(@PathVariable(name = "date1") LocalDate dateFrom,
                                                 @PathVariable(name = "date2") LocalDate dateTo) {
        log.info("!Getting a list of Check for the period " + "from " + dateFrom + " to "+ dateTo);
        return checkService.getAllCheckBetweenDateDto(dateFrom, dateTo);
    }

    @GetMapping(value = "budget/{id}")
    public List<CheckDto> getAllCheckByBudgetId(@PathVariable(name = "id") BigInteger id) {
        log.info("!Getting a list of Checks for a given Income, " + "id=" + id);
        return checkService.getAllCheckByBudgetIdDto(id);
    }

    @GetMapping(value = "budget/{id}/dates/{date1}/{date2}")
    public List<CheckDto> getAllCheckByBudgetBetweenDate(@PathVariable(name = "id") BigInteger id,
                                                         @PathVariable(name = "date1") LocalDate dateFrom,
                                                         @PathVariable(name = "date2") LocalDate dateTo) {
        log.info("!Getting a list of Checks for a given Income, " + "id=" + id +
                 " from " + dateFrom + " to "+ dateTo);
        return checkService.getAllCheckByBudgetBetweenDateDto(id, dateFrom, dateTo);
    }

    @GetMapping(value = "purchase/{id}")
    public List<CheckDto> getAllCheckByPurchaseId(@PathVariable(name = "id") BigInteger id) {
        log.info("!Getting a list of Checks for a given Purchase, " + "id=" + id);
        return checkService.getAllCheckByPurchaseIdDto(id);
    }

    @GetMapping(value = "purchase/{id}/dates/{date1}/{date2}")
    public List<CheckDto> getAllCheckByPurchaseBetweenDate(@PathVariable(name = "id") BigInteger id,
                                                           @PathVariable(name = "date1") LocalDate dateFrom,
                                                           @PathVariable(name = "date2") LocalDate dateTo) {
        log.info("!Getting a list of Checks for a given Purchase, " + "id=" + id +
                 " from " + dateFrom + " to "+ dateTo);
        return checkService.getAllCheckByPurchaseBetweenDateDto(id, dateFrom, dateTo);
    }

}
