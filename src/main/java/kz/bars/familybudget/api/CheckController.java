package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/checks")
@CrossOrigin
public class CheckController {

    private final CheckService checkService;

    @GetMapping(value = "{id}")
    public CheckDto getCheck(@PathVariable(name = "id") Long id) {
        return checkService.getCheckDto(id);
    }

    @PostMapping
    public CheckDto addCheck(@RequestBody CheckDto checkDto) {
        return checkService.addCheckDto(checkDto);
    }

    @PutMapping
    public CheckDto updateCheck(@RequestBody CheckDto checkDto) {
        return checkService.updateCheckDto(checkDto);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCheck(@PathVariable(name = "id") Long id) {
        checkService.deleteCheckDto(id);
    }

    @GetMapping
    public List<CheckDto> getAllCheck() {
        return checkService.getAllCheckDto();
    }

    @GetMapping(value = "budget/{id}")
    public List<CheckDto> getAllCheckByBudgetId(@PathVariable(name = "id") Long id) {
        return checkService.getAllCheckByBudgetIdDto(id);
    }

    @GetMapping(value = "purchase/{id}")
    public List<CheckDto> getAllCheckByPurchaseId(@PathVariable(name = "id") Long id) {
        return checkService.getAllCheckByPurchaseIdDto(id);
    }

}
