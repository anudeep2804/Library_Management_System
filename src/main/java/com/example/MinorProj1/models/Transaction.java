package com.example.MinorProj1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated
    private TransactionType transactionType;

    private String externalTxnId;

    private double payment;

    @ManyToOne
    @JoinColumn
    private Book my_book ;

    @ManyToOne
    @JoinColumn
    private Student my_student;

    @CreationTimestamp
    private Date transactionDate;

}
