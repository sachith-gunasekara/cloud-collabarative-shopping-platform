package lk.ac.kln.stu.gunaseka_ps17050.productservice.repository;

import lk.ac.kln.stu.gunaseka_ps17050.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
