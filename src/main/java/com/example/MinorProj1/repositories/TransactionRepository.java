package com.example.MinorProj1.repositories;

import com.example.MinorProj1.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
