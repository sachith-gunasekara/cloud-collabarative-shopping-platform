package lk.ac.kln.stu.gunaseka_ps17050.paymentservice.controller;

import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto.CreditCardPaymentRequest;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto.MobilePaymentRequest;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto.PaymentResponse;
import lk.ac.kln.stu.gunaseka_ps17050.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit")
    public ResponseEntity<PaymentResponse> payWithCreditCard(@RequestBody CreditCardPaymentRequest creditCardPaymentRequest) {
        PaymentResponse paymentResponse = paymentService.payWithCreditCard(creditCardPaymentRequest);
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }

    @PostMapping("/mobile")
    public ResponseEntity<PaymentResponse> payWithMobile(@RequestBody MobilePaymentRequest mobilePaymentRequest) {
        PaymentResponse paymentResponse = paymentService.payWithMobile(mobilePaymentRequest);
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentStatus(@PathVariable Long id) {
        PaymentResponse paymentResponse = paymentService.getPayment(id);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<PaymentResponse> refund(@PathVariable Long id) {
        PaymentResponse paymentResponse = paymentService.refund(id);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }
}

