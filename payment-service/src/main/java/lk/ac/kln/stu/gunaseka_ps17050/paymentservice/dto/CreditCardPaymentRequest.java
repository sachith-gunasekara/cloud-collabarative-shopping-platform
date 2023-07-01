package lk.ac.kln.stu.gunaseka_ps17050.paymentservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardPaymentRequest {
    private String cardNumber;
    private String cardHolderName;
    private String cvc;
    private BigDecimal amount;
}
