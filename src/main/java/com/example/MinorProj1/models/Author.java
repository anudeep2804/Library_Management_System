package com.example.MinorProj1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private int age;

    @Column(unique = true,nullable = false)

    private String email;

    // here fetch type is written to say that whenever there is a query to get details of author from author table
    // fetch type will tell the hibernate to make a call to book table as well , in our case we need it to get booklist for each author

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> bookList;

    @CreationTimestamp
    private Date addedOn;
}
