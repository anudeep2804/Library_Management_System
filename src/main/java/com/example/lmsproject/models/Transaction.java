package com.example.lmsproject.models;

// Import statements...

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity representing a transaction in the library management system.
 * This class stores information about individual transactions, such as the transaction type,
 * associated book, student involved, and the transaction date.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    // Unique identifier for the transaction.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // External transaction ID that can be used for referencing in external systems or for more detailed tracking.
    private String externalTxnId;

    // Type of the transaction (e.g., BORROW, RETURN, PURCHASE etc.).
    @Enumerated
    private TransactionType transactionType;

    // Amount paid during the transaction. This can be useful for purchase or fine-related transactions.
    private double payment;

    // Relationship mapping to represent the book associated with the transaction.
    @ManyToOne
    @JoinColumn
    private Book book;

    // Relationship mapping to represent the student who carried out the transaction.
    @ManyToOne
    @JoinColumn
    private Student student;

    // Timestamp indicating when the transaction occurred.
    @CreationTimestamp
    private Date transactionDate;
}
