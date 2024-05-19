package com.example.demo.iservices.services;

import com.example.demo.entities.PaymentMethod;
import com.example.demo.iservices.IPaymentMethodService;
import com.example.demo.repositories.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {
    @Autowired
    private IPaymentMethodRepository iPaymentMethodRepository;

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return iPaymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return iPaymentMethodRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        iPaymentMethodRepository.deleteById(id);
    }

    @Override
    public PaymentMethod update(PaymentMethod paymentMethod) {
        Optional<PaymentMethod> existingPaymentMethod = iPaymentMethodRepository.findById(paymentMethod.getId());
        if (existingPaymentMethod.isPresent()) {
            PaymentMethod updatedPaymentMethod = existingPaymentMethod.get();
            updatedPaymentMethod.setType(paymentMethod.getType());
            return iPaymentMethodRepository.save(updatedPaymentMethod);
        } else {
            throw new RuntimeException("PaymentMethod not found with id: " + paymentMethod.getId());
        }
    }

}
