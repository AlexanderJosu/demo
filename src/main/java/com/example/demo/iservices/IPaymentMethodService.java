package com.example.demo.iservices;



import com.example.demo.entities.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService {
    //save
    public PaymentMethod save(PaymentMethod paymentMethod);

    //findAll
    public List<PaymentMethod> findAll();

    //delete
    public void delete(Long id);

    //update
    public PaymentMethod update(PaymentMethod paymentMethod);
}
