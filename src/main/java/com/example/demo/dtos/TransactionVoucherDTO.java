package com.example.demo.dtos;

import com.example.demo.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVoucherDTO {
    private Long id;
    private Double total_amount;
    private SecurityProperties.User user;
    private PaymentMethod paymentMethod;
}
