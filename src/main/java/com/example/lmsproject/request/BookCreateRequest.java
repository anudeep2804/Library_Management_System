package com.example.lmsproject.request;

import com.example.lmsproject.models.Author;
import com.example.lmsproject.models.Book;
import com.example.lmsproject.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Represents a request to create a new book entity for the LMS system.
 * This class captures all the necessary fields required for book creation.

 */
@Getter
@Setter
@AllArgsConstructor  // Constructor that initializes all fields.
@NoArgsConstructor   // No-args constructor for creating an empty object.
@Builder            // Enables the use of the Builder pattern for creating objects.
public class BookCreateRequest {

    // Title of the book.
    @NotBlank   // Ensures that the name field is neither null nor just whitespace.
    private String name;

    // Author of the book.
    @NotNull    // Ensures that the author is provided (not null).
    private Author author;

    // Cost of the book.
    @Positive   // Ensures that the cost is a positive valu
    private int cost;

    // Genre of the book (e.g., Fiction, Non-Fiction, etc.).
    @NotNull    // Ensures that a genre is provided for the book.
    private Genre genre;

    /**
     * Converts this request object into a Book entity.
     * @return Book entity built from the request's fields.
     */
    public Book to() {
        return Book.builder()
                .cost(this.cost)
                .genre(this.genre)
                .name(this.name)
                .author(this.author)
                .build();
    }
}
