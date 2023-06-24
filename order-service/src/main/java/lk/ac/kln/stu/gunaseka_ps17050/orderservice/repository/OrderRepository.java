package lk.ac.kln.stu.gunaseka_ps17050.orderservice.repository;

import lk.ac.kln.stu.gunaseka_ps17050.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
