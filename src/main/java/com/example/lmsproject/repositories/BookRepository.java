package com.example.lmsproject.repositories;

import com.example.lmsproject.models.Book;
import com.example.lmsproject.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for CRUD operations related to the Book entity.
 * This interface extends JpaRepository which provides JPA related methods
 * that can be used with the Book entity.
 */

public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findByName(String name);


    List<Book> findByAuthor_Name(String authorName);

    List<Book> findByGenre(Genre genre);

    List<Book> findByCostLessThan(int cost);
    List<Book> findByCostGreaterThan(int cost);
    List<Book> findByCostLessThanEqual(int cost);
    List<Book> findByCostGreaterThanEqual(int cost);

//    @Query("select b from Book b where b.:filterType = ?2")
//    List<Book> findEqualsGeneric(String filterType, String value);
}
