package com.example.demo.iservices;


import com.example.demo.entities.TransactionDetail;

public interface ITransactionDetailService {
    //delete
    public void delete(Long id);

    //findall
    public Iterable<TransactionDetail> getAll();



}
