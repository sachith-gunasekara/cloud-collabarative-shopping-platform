package lk.ac.kln.stu.gunaseka_ps17050.inventoryservice;

import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.model.Inventory;
import lk.ac.kln.stu.gunaseka_ps17050.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}
