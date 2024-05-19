package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction_detail")
public class TransactionDetail {
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_detail_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "transaction_voucher_id", nullable = false)
    private TransactionVoucher transactionVoucher;

}
