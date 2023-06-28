package lk.ac.kln.stu.gunaseka_ps17050.productservice.service;

import jakarta.ws.rs.NotFoundException;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductRequest;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductResponse;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.model.Product;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Successfully saved product {} to the database", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
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
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());

                    return this.mapToProductResponse(productRepository.save(product));
                })
                .orElseThrow(() ->
                        new NotFoundException("Product with id: " + id + " was not found."));
    }
}
