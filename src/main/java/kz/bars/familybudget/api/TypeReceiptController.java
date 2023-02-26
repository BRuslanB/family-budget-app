//package kz.bars.familybudget.api;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/receipts")
//@CrossOrigin
//public class TypeReceiptController {
//
//    private final TypeReceiptService typeReceiptService;
//
//    @GetMapping
//    public List<TypeReceipt> getAllTypeReceipt() {
//        return typeReceiptService.getAllTypeReceipt();
//    }
//
//    @GetMapping(value = "{id}")
//    public TypeReceipt getTypeReceipt(@PathVariable(name = "id") Long id) {
//        return typeReceiptService.getTypeReceipt(id);
//    }
//
//    @PostMapping
//    public TypeReceipt addTypeReceipt(@RequestBody TypeReceipt typeReceipt) {
//        return typeReceiptService.addTypeReceipt(typeReceipt);
//    }
//
//    @PutMapping
//    public TypeReceipt updateTypeReceipt(@RequestBody TypeReceipt typeReceipt) {
//        return typeReceiptService.updateTypeReceipt(typeReceipt);
//    }
//
//    @DeleteMapping(value = "{id}")
//    public void deleteTypeReceipt(@PathVariable(name = "id") Long id) {
//        typeReceiptService.deleteTypeReceipt(id);
//    }
//
//}
