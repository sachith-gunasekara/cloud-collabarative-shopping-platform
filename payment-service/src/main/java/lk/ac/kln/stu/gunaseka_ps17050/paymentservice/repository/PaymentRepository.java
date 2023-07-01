package lk.ac.kln.stu.gunaseka_ps17050.paymentservice.repository;

import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
