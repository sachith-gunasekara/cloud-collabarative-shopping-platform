package lk.ac.kln.stu.gunaseka_ps17050.productservice.controller;

import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductRequest;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.dto.ProductResponse;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.model.Product;
import lk.ac.kln.stu.gunaseka_ps17050.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> searchProduct(@RequestParam String query){
        return productService.searchProduct(query);
    }
}
