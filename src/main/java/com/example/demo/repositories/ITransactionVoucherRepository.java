package com.example.demo.repositories;

import com.example.demo.entities.TransactionVoucher;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITransactionVoucherRepository extends JpaRepository<TransactionVoucher, Long> {
    Optional<TransactionVoucher> findFirstByUser(Optional<User> user);
}
