package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction_voucher")
public class TransactionVoucher {
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_voucher_id")
    private Long id;

    //Monto total de transaccion en comprobante
    @Column(name = "total_amount", nullable = false)
    private Double total_amount;

    //Usuario(donante)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Metodo_De_Pago
    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
}
