package com.example.demo.repositories;

import com.example.demo.entities.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
}
