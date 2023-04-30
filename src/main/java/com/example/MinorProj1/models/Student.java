package com.example.MinorProj1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor

public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentid;


    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    private String studentname;
    @Column(unique = true,nullable = false)
    private String contact;

    @Column(unique = true,nullable = false)

    private String email;

    private String address;

    @CreationTimestamp

    private Date CreateDate;


    @UpdateTimestamp
    private Date ModifiedDate;

  // mappedBy will define this booklist or book table which column will map to student,
    // lets say we have two columns of type Student in book, we need to provide which column should be used
    // for hibernate made query : select * from book where student_id = ?
    @OneToMany(mappedBy = "student") // for back reference
    private List<Book> bookList;

    @OneToMany(mappedBy = "my_student")
    private List<Transaction> transactionList;


}
