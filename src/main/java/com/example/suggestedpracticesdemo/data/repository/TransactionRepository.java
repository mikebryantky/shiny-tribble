package com.example.suggestedpracticesdemo.data.repository;

import com.example.suggestedpracticesdemo.data.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}