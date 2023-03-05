package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/purchases")
@CrossOrigin
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public List<PurchaseDto> getAllPurchase() {
        return purchaseService.getAllPurchaseDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    public List<PurchaseDto> getAllPurchaseBetweenDate(@PathVariable(name = "date1") LocalDate dateFrom,
                                                       @PathVariable(name = "date2") LocalDate dateTo) {
        return purchaseService.getAllPurchaseBetweenDateDto(dateFrom, dateTo);
    }

}
