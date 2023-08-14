package com.example.lmsproject.response;

import com.example.lmsproject.models.Author;
import com.example.lmsproject.models.Genre;
import com.example.lmsproject.models.Student;
import com.example.lmsproject.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Represents the response returned after searching for a book in the LMS system.
 * This class encapsulates the relevant details about the book including associated entities.
 */
@AllArgsConstructor  // Constructor that initializes all fields.
@NoArgsConstructor   // No-args constructor for creating an empty object.
@Getter
@Setter
@Builder            // Enables the use of the Builder pattern for creating objects.
public class BookSearchResponse {

    // Unique identifier for the book.
    private int id;

    // Title of the book.
    private String name;

    // Cost of the book.
    private int cost;

    // Genre of the book (e.g., Fiction, Non-Fiction, etc.).
    private Genre genre;

    // Author details associated with the book.
    // We ignore bookList and addedOn fields from the author when serializing to avoid recursion and unnecessary data.
    @JsonIgnoreProperties({"bookList", "addedOn"})
    private Author author;

    // If the book is associated with a student (e.g., borrowed or rented), this provides details of the student.
    private Student student;

    // List of transactions associated with this book.
    private List<Transaction> transactionList;

    // Timestamp indicating when the book was added to the LMS system.
    private Date createdOn;

    // Timestamp of the last update to the book's details.
    private Date updatedOn;
}
