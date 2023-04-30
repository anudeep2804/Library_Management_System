package com.example.MinorProj1.repositories;

import com.example.MinorProj1.models.Book;
import com.example.MinorProj1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    // select * from book where name = ?1
    List<Book> findByName(String name);

    // select * from book, author where author.name = ?1 and author.id = book.author_id
    List<Book> findByAuthor_Name(String authorName);

    List<Book> findByGenre(Genre genre);

    @Query(value = "Select * from Book where COST<100", nativeQuery = true )
    List<Book> findByCostLessThan(int value);

}
