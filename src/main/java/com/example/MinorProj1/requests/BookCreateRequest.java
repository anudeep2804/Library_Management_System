package com.example.MinorProj1.requests;

/*we have created this POJO to handle all inputs required for creating a book */

import com.example.MinorProj1.models.Author;
import com.example.MinorProj1.models.Book;
import com.example.MinorProj1.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  BookCreateRequest {

   @NotBlank
    private String name;

    @NotNull

    private Author author;

    @Positive

    private int cost;

    @NotNull
    private Genre genre;

    public Book to(){

     return Book.builder().name(this.name).
             genre(this.genre).cost(this.cost).
             author(this.author).build();
    }




}
