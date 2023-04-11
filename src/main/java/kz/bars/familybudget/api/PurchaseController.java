package kz.bars.familybudget.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.bars.familybudget.dto.PurchaseDto;
import kz.bars.familybudget.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/purchases")
@CrossOrigin
@Log4j2
@SecurityRequirement(name = "javainuseapi")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PurchaseDto> getAllPurchase() {
        log.info("!Getting a list of Purchase");
        return purchaseService.getAllPurchaseDto();
    }

    @GetMapping(value = "dates/{date1}/{date2}")
    @PreAuthorize("isAuthenticated()")
    public List<PurchaseDto> getAllPurchaseBetweenDate(@PathVariable(name = "date1") LocalDate dateFrom,
                                                       @PathVariable(name = "date2") LocalDate dateTo) {
        log.info("!Getting a list of Purchase for the period " + "from " + dateFrom + " to "+ dateTo);
        return purchaseService.getAllPurchaseBetweenDateDto(dateFrom, dateTo);
    }

}
