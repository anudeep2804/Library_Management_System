package com.example.MinorProj1.response;

import com.example.MinorProj1.models.Author;
import com.example.MinorProj1.models.Genre;
import com.example.MinorProj1.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookSearchResponse {
    private int id;
    private String name;
    private int cost;

    private Genre genre;

    @JsonIgnoreProperties({"bookList", "addedOn"})
    private Author author;
    private Student student;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

}
