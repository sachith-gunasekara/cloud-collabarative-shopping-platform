package lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class PaymentResponse {
    private Long id;
    private String paymentType;
    private BigDecimal amount;
    private String message;

    public PaymentResponse(Long id, String paymentType, BigDecimal amount) {
        this.id = id;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public PaymentResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
