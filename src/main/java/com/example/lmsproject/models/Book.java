package com.example.lmsproject.models;

import com.example.lmsproject.response.BookSearchResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a Book in the library management system.
 * This entity has attributes that define properties of a book, its associated author,
 * student who borrowed it, and its transaction history.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    // Primary key for the Book entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Name of the book
    private String name;

    // Cost of the book
    private int cost;

    // Genre of the book, stored as a string in the database
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // Relationship mapping to represent the Author of the book
    @ManyToOne
    @JoinColumn
    private Author author;

    // Relationship mapping to represent the Student who borrowed the book
    @ManyToOne
    @JoinColumn
    private Student student;

    // Relationship mapping to represent the list of transactions associated with the book.
    // The 'mappedBy' attribute signifies that the 'book' field in the 'Transaction' entity owns the relationship.
    // This means that the 'Transaction' entity contains a reference (foreign key) to the 'Book' entity.
    // As a result, changes to the relationship are made on the 'Transaction' side.
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;


    // Timestamp capturing when the book entity was created
    @CreationTimestamp
    private Date createdOn;

    // Timestamp capturing the last time the book entity was updated
    @UpdateTimestamp
    private Date updatedOn;

    /**
     * Converts the Book entity into a BookSearchResponse object.
     * This method is useful for returning search results in a specific response format.
     *
     * @return A BookSearchResponse object with populated attributes from the Book entity.
     */
    public BookSearchResponse to(){
        return BookSearchResponse.builder()
                .id(id)
                .name(name)
                .author(author)
                .cost(cost)
                .genre(genre)
                .student(student)
                .transactionList(transactionList)
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .build();
    }
}
