package lk.ac.kln.stu.gunaseka_ps17050.productservice.service;

import jakarta.ws.rs.NotFoundException;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.InventoryRequest;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductRequest;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductResponse;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.model.Product;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .skuCode(productRequest.getSkuCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        InventoryRequest inventoryRequest = InventoryRequest.builder()
                        .skuCode(productRequest.getSkuCode())
                                .quantity(productRequest.getQuantity())
                                        .build();

        productRepository.save(product);

        webClientBuilder.build().post()
                        .uri("http://inventory-service/api/inventory")
                                .bodyValue(inventoryRequest)
                                        .retrieve()
                                                .onStatus(
                                                        HttpStatusCode::is2xxSuccessful,
                                                        response -> {
                                                            if(response.statusCode().equals(HttpStatus.CREATED)){
                                                                log.info("Successfully saved product {} to the database", product.getId());
                                                            }

                                                            return Mono.empty();
                                                        })
                .bodyToMono(Void.class)
                .doOnError(
                        error -> log.error("Error occured ", error)
                )
                .subscribe();
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .skuCode(product.getSkuCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponse updateProduct(Long id, Product updatedProduct){
        return productRepository.findById(id)
                .map(product -> {
                    product.setSkuCode(updatedProduct.getSkuCode());
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());

                    return this.mapToProductResponse(productRepository.save(product));
                })
                .orElseThrow(() ->
                        new NotFoundException("Product with id: " + id + " was not found."));
    }

    public List<ProductResponse> searchProduct(String query) {
        List<Product> products = productRepository.findBySearchQuery(query);

        return products.stream().map(this::mapToProductResponse).toList();
    }
}
