package com.example.lmsproject.services;

import com.example.lmsproject.models.Author;
import com.example.lmsproject.models.Book;
import com.example.lmsproject.models.Genre;
import com.example.lmsproject.repositories.AuthorRepository;
import com.example.lmsproject.repositories.BookRepository;
import com.example.lmsproject.request.BookCreateRequest;
import com.example.lmsproject.request.BookFilterType;
import com.example.lmsproject.request.BookSearchOperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service responsible for handling operations related to Book entity.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Creates or updates a book in the database.
     * If the author of the book doesn't exist in the database, it's added.
     * @param bookCreateRequest The request containing book and its author details.
     */
    public void createOrUpdate(BookCreateRequest bookCreateRequest){
        Book book = bookCreateRequest.to();
        createOrUpdateFromBook(book);
    }

    /**
     * Helper method to create or update a book in the database.
     * If the author of the book doesn't exist in the database, it's added.
     * @param book The book object to be saved.
     */
    public void createOrUpdateFromBook(Book book){
        Author author = book.getAuthor();

        // Find if the author with the given email already exists in the database.
        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        // If the author does not exist, save the author to the database.
        if(authorFromDB == null){
            authorFromDB = authorRepository.save(author);
        }

        // Associate the book with the retrieved (or newly created) author and save the book.
        book.setAuthor(authorFromDB);
        bookRepository.save(book);
    }

    /**
     * Provides basic book search functionality based on a single filter type and value.
     * @param bookFilterType The type of filter (e.g., NAME, GENRE).
     * @param value The value for the filter (e.g., "Fiction" for GENRE).
     * @return A list of books that match the criteria.
     */
    public List<Book> findByCriteria(BookFilterType bookFilterType, String value){
        switch (bookFilterType){
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_Name(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            case BOOK_ID:
                return bookRepository
                        .findAllById(Collections.singletonList(Integer.parseInt(value)));
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Provides advanced book search functionality considering both filter type and operation type.
     * For example, it can be used to find books by an author that are priced below a certain amount.
     * @param bookFilterType The type of filter (e.g., AUTHOR_NAME).
     * @param value The value for the filter (e.g., "J.K. Rowling" for AUTHOR_NAME).
     * @param bookSearchOperationType The operation type for the search (e.g., PRICE_BELOW).
     * @return A list of books that match the criteria.
     */
    public List<Book> findByCriteriaandOpstype(BookFilterType bookFilterType, String value, BookSearchOperationType bookSearchOperationType){
        switch (bookFilterType){
            case NAME:
                if(bookSearchOperationType == BookSearchOperationType.EQUALS) {
                    return bookRepository.findByName(value);
                }
                break;
            case AUTHOR_NAME:
                if(bookSearchOperationType == BookSearchOperationType.EQUALS) {
                    return bookRepository.findByAuthor_Name(value);
                }
                break;
            case GENRE:
                if(bookSearchOperationType == BookSearchOperationType.EQUALS) {
                    return bookRepository.findByGenre(Genre.valueOf(value));
                }
                break;
            case COST:  // Using COST in BookFilterType
                int costValue;
                try {
                    costValue = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return new ArrayList<>();
                }
                switch(bookSearchOperationType) {
                    case LESS_THAN:
                        return bookRepository.findByCostLessThan(costValue);
                    case LESS_THAN_EQUAL_TO:
                        return bookRepository.findByCostLessThanEqual(costValue);
                    case GREATER_THAN:
                        return bookRepository.findByCostGreaterThan(costValue);
                    case GREATER_THAN_EQUAL_TO:
                        return bookRepository.findByCostGreaterThanEqual(costValue);
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return new ArrayList<>();
    }


    /**
     * Provides a robust search functionality allowing multiple filter criteria, values, and operation types.
     * For example, finding all "Fiction" books priced below $15 written by authors from "UK".
     * @param bookFilterType List of filter types.
     * @param value List of corresponding values for the filters.
     * @param bookSearchOperationType List of operation types for the search.
     * @return A list of books that match the combined criteria.
     */
    public List<Book> findByCriteriaandOpstype(List<BookFilterType> bookFilterType,
                                               List<String> value,
                                               List<BookSearchOperationType> bookSearchOperationType){

        return null;
    }
}
