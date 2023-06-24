package lk.ac.kln.stu.gunaseka_ps17050.inventoryservice;

import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.model.Inventory;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory item1 = new Inventory();
			item1.setSkuCode("iphone_13_silver");
			item1.setQuantity(50);

			Inventory item2 = new Inventory();
			item2.setSkuCode("iphone_13_sierra_blue");
			item2.setQuantity(0);

			inventoryRepository.save(item1);
			inventoryRepository.save(item2);
		};
	}
}
