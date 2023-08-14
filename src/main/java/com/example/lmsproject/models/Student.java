package com.example.lmsproject.models;

// Import statements...

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a student in the library management system.
 * This class stores personal information about a student, the list of books they have,
 * and the transactions they've been a part of.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {

    // Unique identifier for the student.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Name of the student.
    private String name;

    // Unique contact number of the student.
    @Column(unique = true, nullable = false)
    private String contact;

    // Address of the student.
    private String address;

    // Unique email address for the student.
    @Column(unique = true)
    private String email;

    // Enum indicating the status of the student's account.
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    // Timestamp indicating when the student record was created.
    @CreationTimestamp
    private Date createdOn;

    // Timestamp indicating the last time the student record was updated.
    @UpdateTimestamp
    private Date updatedOn;

    // Relationship mapping to represent the list of books associated with the student.
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    // Relationship mapping to represent the list of transactions associated with the student.
    @OneToMany(mappedBy = "student")
    private List<Transaction> transactionList;

    // One-to-one relationship between Student and MyUser.
    // This links the student entity to the associated user information in the MyUser table.
    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student", "admin", "password"})
    MyUser myUser;
}
