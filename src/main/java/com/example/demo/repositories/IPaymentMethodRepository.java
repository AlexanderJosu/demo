package com.example.demo.repositories;

import com.example.demo.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {
}
