package com.example.demo.iservices.services;

import com.example.demo.entities.TransactionDetail;
import com.example.demo.iservices.ITransactionDetailService;
import com.example.demo.repositories.ITransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionDetailServiceImpl implements ITransactionDetailService {
    @Autowired
    private ITransactionDetailRepository iTransactionDetailRepository;

    @Override
    public void delete(Long id) {
        iTransactionDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<TransactionDetail> getAll() {
        return iTransactionDetailRepository.findAll();
    }


}
