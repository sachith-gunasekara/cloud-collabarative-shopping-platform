package lk.ac.kln.stu.gunaseka_ps17050.productservice;

import lk.ac.kln.stu.gunaseka_ps17050.productservice.controller.ProductController;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductRequest;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.repository.ProductRepository;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class ProductServiceApplication {

	private final ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(ProductRepository productRepository){
		return args -> {
			List<ProductRequest> productRequests = new ArrayList<>();

			String[] names = {"Macbook Pro 13", "iPhone 14 Pro", "Airpods Pro(2nd Gen)", "Troubadour Backpack", "Nike Sneakers"};
			String[] descriptions = {"High-performance laptop for work and gaming", "Latest smartphone with advanced features", "Noise-cancelling headphones with great sound quality", "Durable backpack with multiple compartments", "Comfortable sneakers for everyday wear"};
			BigDecimal[] prices = {new BigDecimal("999.99"), new BigDecimal("799.99"), new BigDecimal("199.99"), new BigDecimal("49.99"), new BigDecimal("99.99")};
			Integer[] quantities = {10, 20, 30, 40, 50};

			for (int i = 0; i < names.length; i++) {
				String skuCode = "SKU" + (i + 1);
				String name = names[i];
				String description = descriptions[i];
				BigDecimal price = prices[i];
				Integer quantity = quantities[i];

				ProductRequest product = ProductRequest.builder()
						.skuCode(skuCode)
						.name(name)
						.description(description)
						.price(price)
						.quantity(quantity)
						.build();
				productRequests.add(product);
			}

			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				for (ProductRequest productRequest : productRequests) {
					productService.createProduct(productRequest);
				}
			});
		};
	}
}
