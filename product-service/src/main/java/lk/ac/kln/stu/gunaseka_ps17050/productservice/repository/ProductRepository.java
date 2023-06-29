package lk.ac.kln.stu.gunaseka_ps17050.productservice.repository;

import lk.ac.kln.stu.gunaseka_ps17050.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "WHERE lower(p.skuCode) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(p.name) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(p.description) LIKE lower(concat('%', :query, '%'))")
    List<Product> findBySearchQuery(String query);
}
