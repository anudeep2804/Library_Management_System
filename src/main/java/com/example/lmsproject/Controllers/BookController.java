package com.example.lmsproject.controllers;

import com.example.lmsproject.request.BookCreateRequest;
import com.example.lmsproject.request.BookFilterType;
import com.example.lmsproject.request.BookSearchOperationType;
import com.example.lmsproject.response.BookSearchResponse;
import com.example.lmsproject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller handles HTTP requests related to Book operations.
 */
@RestController
public class BookController {

    // Service for interacting with Book related data.
    @Autowired
    BookService bookService;

    /**
     * Endpoint to create or update a book.
     * @param bookCreateRequest The request payload containing book details.
     */
    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        bookService.createOrUpdate(bookCreateRequest);
    }

    /**
     * Endpoint to search for books based on a filter and value.
     * @param bookFilterType The type of filter (e.g., author, title).
     * @param value The value to search for.
     * @return A list of books matching the search criteria.
     */
    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(
            @RequestParam("filter")BookFilterType bookFilterType,
            @RequestParam("value") String value){
        return bookService
                .findByCriteria(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

    /**
     * Enhanced search endpoint considering both filter type and an operation type.
     * @param bookFilterType The type of filter.
     * @param value The value to search for.
     * @param bookSearchOperationType The type of operation (e.g., EQUALS, LIKE).
     * @return A list of books matching the search criteria.
     */
    @GetMapping("/books/search/robust")
    public List<BookSearchResponse> findBooks2(
            @RequestParam("filter")BookFilterType bookFilterType,
            @RequestParam("value") String value,
            @RequestParam("bookSearchOperationType") BookSearchOperationType bookSearchOperationType){
        return bookService
                .findByCriteriaandOpstype(bookFilterType, value, bookSearchOperationType)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

    /**
     * Advanced search endpoint considering multiple filters and operation types.
     * @param bookFilterType The list of filter types.
     * @param value The list of values to search for.
     * @param bookSearchOperationType The list of operation types.
     * @return A list of books matching the search criteria.
     */
    @GetMapping("/books/search/robust2")
    public List<BookSearchResponse> findBooks3(
            @RequestParam("filter")List<BookFilterType> bookFilterType,
            @RequestParam("value") List<String> value,
            @RequestParam("bookSearchOperationType") List<BookSearchOperationType> bookSearchOperationType){
        return bookService
                .findByCriteriaandOpstype(bookFilterType, value, bookSearchOperationType)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

    // Placeholder for future author search functionality
}
