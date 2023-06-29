package lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.service;

import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryRequest;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryResponse;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryUpdateRequest;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.dto.InventoryUpdateResponse;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.model.Inventory;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void createInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();

        inventoryRepository.save(inventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }

    public InventoryUpdateResponse updateInventory(String skuCode, InventoryUpdateRequest inventoryUpdateRequest) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        inventory.setQuantity(inventoryUpdateRequest.getQuantity());

        return this.mapToInventoryUpdateResponse(inventoryRepository.save(inventory));
    }

    private InventoryUpdateResponse mapToInventoryUpdateResponse(Inventory inventory){
        return  InventoryUpdateResponse.builder()
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .build();
    }
}
