package lk.ac.kln.stu.gunaseka_ps17050.paymentservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public abstract class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amount;
    private String paymentType;
}

