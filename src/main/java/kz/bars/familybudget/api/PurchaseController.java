package kz.bars.familybudget.api;

import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
