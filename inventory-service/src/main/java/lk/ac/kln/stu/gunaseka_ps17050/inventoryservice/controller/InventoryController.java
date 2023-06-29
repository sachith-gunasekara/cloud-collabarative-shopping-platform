package lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.controller;

import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryRequest;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryResponse;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryUpdateRequest;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryUpdateResponse;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.createInventory(inventoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @PutMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryUpdateResponse updateInventory(@PathVariable String skuCode, @RequestBody InventoryUpdateRequest inventoryUpdateRequest) {
        return inventoryService.updateInventory(skuCode, inventoryUpdateRequest);
    }
}
