package lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.controller;

import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryResponse;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
