package com.example.lmsproject.repositories;

import com.example.lmsproject.models.Book;
import com.example.lmsproject.models.Student;
import com.example.lmsproject.models.Transaction;
import com.example.lmsproject.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CRUD operations related to the Book entity.
 * This interface extends JpaRepository which provides JPA related methods
 * that can be used with the Transcation entity.
 */

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
            Book book,Student student, TransactionType transactionType
    );

}
