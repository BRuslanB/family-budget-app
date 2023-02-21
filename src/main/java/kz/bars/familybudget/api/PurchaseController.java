package kz.bars.familybudget.api;

import kz.bars.familybudget.model.Purchase;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/purchase")
@CrossOrigin
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public List<Purchase> getAllPurchase() {
        return purchaseService.getAllPurchase();
    }

    @GetMapping(value = "{id}")
    public Purchase getPurchase(@PathVariable(name = "id") Long id) {
        return purchaseService.getPurchase(id);
    }

    @PostMapping
    public Purchase addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.addPurchase(purchase);
    }

    @PutMapping
    public Purchase updatePurchase(@RequestBody Purchase purchase) {
        return purchaseService.updatePurchase(purchase);
    }

    @DeleteMapping(value = "{id}")
    public void deletePurchase(@PathVariable(name = "id") Long id) {
        purchaseService.deletePurchase(id);
    }

}
