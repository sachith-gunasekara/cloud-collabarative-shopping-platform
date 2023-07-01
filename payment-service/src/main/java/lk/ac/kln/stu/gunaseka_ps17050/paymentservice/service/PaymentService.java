package lk.ac.kln.stu.gunaseka_ps17050.paymentservice.service;

import jakarta.ws.rs.NotFoundException;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto.CreditCardPaymentRequest;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto.MobilePaymentRequest;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto.PaymentResponse;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.model.CreditCardPayment;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.model.MobilePayment;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.model.Payment;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponse payWithCreditCard(CreditCardPaymentRequest creditCardPaymentRequest) {
        CreditCardPayment payment = new CreditCardPayment();
        payment.setAmount(creditCardPaymentRequest.getAmount());
        // Here you would call the payment gateway to process the transaction using the creditCardPaymentRequest object
        // If the transaction is successful, save the payment object to the database
        paymentRepository.save(payment);
        return new PaymentResponse(payment.getId(), "Credit Card", payment.getAmount());
    }

    public PaymentResponse payWithMobile(MobilePaymentRequest mobilePaymentRequest) {
        MobilePayment payment = new MobilePayment();
        payment.setAmount(mobilePaymentRequest.getAmount());
        // Here you would call the payment gateway to process the transaction using the mobilePaymentRequest object
        // If the transaction is successful, save the payment object to the database
        paymentRepository.save(payment);
        return new PaymentResponse(payment.getId(), "Mobile", payment.getAmount());
    }

    public PaymentResponse getPayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found"));
        return new PaymentResponse(payment.getId(), payment.getPaymentType(), payment.getAmount());
    }

    public PaymentResponse refund(Long id) {
        // Here you would call the payment gateway to process the refund
        // If the refund is successful, delete the payment object from the database
        paymentRepository.deleteById(id);
        return new PaymentResponse(id, "Refund successful");
    }
}

