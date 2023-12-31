package lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.repository;

import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    Inventory findBySkuCode(String skuCode);
}
