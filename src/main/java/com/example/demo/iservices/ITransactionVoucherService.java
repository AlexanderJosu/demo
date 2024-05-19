package com.example.demo.iservices;



import com.example.demo.entities.TransactionVoucher;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface ITransactionVoucherService {
    //save
    public TransactionVoucher save(TransactionVoucher transactionVoucher);

    //update
    public TransactionVoucher update(TransactionVoucher transactionVoucher);

    //delete
    public void delete(Long id);

    //findall
    public List<TransactionVoucher> findAll();
    Optional<TransactionVoucher> findFirstByUser(Optional<User> user);
}
