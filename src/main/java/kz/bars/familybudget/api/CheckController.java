package kz.bars.familybudget.api;

import kz.bars.familybudget.model.Check;
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

    @GetMapping
    public List<Check> getAllCheck() {
        return checkService.getAllCheck();
    }

    @GetMapping(value = "{id}")
    public Check getCheck(@PathVariable(name = "id") Long id) {
        return checkService.getCheck(id);
    }

    @PostMapping
    public Check addCheck(@RequestBody Check check) {
        return checkService.addCheck(check);
    }

    @PutMapping
    public Check updateCheck(@RequestBody Check check) {
        return checkService.updateCheck(check);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCheck(@PathVariable(name = "id") Long id) {
        checkService.deleteCheck(id);
    }

}
