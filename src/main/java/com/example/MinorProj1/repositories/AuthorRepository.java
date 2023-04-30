package com.example.MinorProj1.repositories;

import com.example.MinorProj1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query(value = "select a from author a where a.email = ?1", nativeQuery = true)
    public Author getAuthorGivenEmailIdNativeQuery(String author_email); // based on sql queries

    @Query("select a from Author a where a.email = ?1")
    public Author getAuthorGivenEmailIdJPQL(String author_email); // Based on entities

    public Author findByEmail(String email); // we can also write in this kind of specific naming convention and
    // Hibernate will be able to parse and understand

    // you have to find all the authors above the age of 30,
    // living in india and their name starting with A

    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith
            (int age, String country, String prefix);
}
