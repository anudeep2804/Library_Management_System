package com.example.MinorProj1.Services;

import com.example.MinorProj1.models.Author;
import com.example.MinorProj1.models.Book;
import com.example.MinorProj1.models.Genre;
import com.example.MinorProj1.repositories.AuthorRepository;
import com.example.MinorProj1.repositories.BookRepository;
import com.example.MinorProj1.requests.BookCreateRequest;
import com.example.MinorProj1.requests.BookFilterType;

import com.example.MinorProj1.requests.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;



    public void create(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.to();
        //bookRepository.save(book); // this will throw error with foreign key constraint as corresponding author is not saved

        create(book);
    }

    public void create(Book book){
        Author author = book.getAuthor();

        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if (authorFromDB == null) {
            authorFromDB = authorRepository.save(author);
        }
        //book.setAuthor(author); this is not required as author in book object and author are pointing to same mem loc, so we just need id and other details will be
        // updated from the book only

        // book.setId(1); // we can set id here but this will throw an error 'foreign key constraint failed' as author is not saved yet , parent object not present. so save author first


        book.setAuthor(authorFromDB);
        bookRepository.save(book);

    }

    public Book findByid(int bid){
        return bookRepository.findById(bid).orElse(null);
    }
        public List<Book> find(BookFilterType bookFilterType, String value ){

            switch (bookFilterType){
                case NAME:
                    return bookRepository.findByName(value);
                case AUTHOR_NAME:
                    return bookRepository.findByAuthor_Name(value);
                case GENRE:
                    return bookRepository.findByGenre(Genre.valueOf(value));

                case BOOK_ID:
                    return bookRepository.findAllById(Collections.singletonList(Integer.parseInt(value)));
                default:
                    return new ArrayList<>();
            }
        }

    public List<Book> find2(BookFilterType bookFilterType , String value ,OperationType operationType, String operationtype_value ){

        /* for example you can implement the filter bookcost by price less than 100, bookfiletertype=COST and operationType=LESS_THAN
        *
        * code would be like
        *
        * if(bookFilterType='COST'|| operationType='LESS_THAN'*){
           return bookRepository.findByCostLessThan()
         */

        switch (bookFilterType){
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_Name(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            default:
                return new ArrayList<>();
        }
    }

        
    }

