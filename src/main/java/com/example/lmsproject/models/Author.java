package com.example.lmsproject.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity representing an Author in the library management system.
 * The Author entity provides details about an author, such as their name, country, and age.
 * Additionally, each author can be associated with multiple books, establishing a one-to-many relationship with the Book entity.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Author {

    // Primary key for the Author entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private int id;

    // Name of the author.
    private String name;

    // Country of origin for the author.
    // Using "land" as the column name in the database.
    @Column(name = "land")
    private String country;

    // Age of the author.
    private int age;

    // Email address of the author.
    // The email is a unique field in the database and cannot be null.
    @Column(unique = true, nullable = false)
    private String email;

    // Relationship mapping to represent the list of books associated with this author.
    // The mappedBy attribute indicates that the "author" field in the Book entity owns the relationship.
    // Using FetchType.EAGER to load the list of books immediately when an Author entity is fetched.
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> bookList;

    // Timestamp capturing when the author entity was added to the system.
    @CreationTimestamp
    private Date addedOn;
}
