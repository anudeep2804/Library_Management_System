package com.example.MinorProj1.Controllers;

import com.example.MinorProj1.Services.BookService;
import com.example.MinorProj1.requests.BookCreateRequest;
import com.example.MinorProj1.requests.BookFilterType;
import com.example.MinorProj1.requests.OperationType;
import com.example.MinorProj1.response.BookSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class BookController {


    @Autowired
    BookService bookService;



    // @RequestBody - JSON to domain object with automatic deserialization
    // @Valid - make sures all the validations defined on the POJO are enforced
    @PostMapping("/book")
    public void CreateBook( @Valid @RequestBody BookCreateRequest bookCreateRequest){

      bookService.create(bookCreateRequest);

    }

    // initially when you run this API you will see stackoverflow error , this is because it will trigger a infinite loop
    // as Book entity / table has refernce to author and on author table again we have refernce to Book as we are trying
    // to retrive list of books so author table will acess book table and book table will again access author table and it goes on
    // to avoid this deadlock , we use properties like @json
    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(
            @RequestParam("filter") BookFilterType bookFilterType,
            @RequestParam("value") String value){

        return bookService
                .find(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

    @GetMapping("/books/search/robust")
    public List<BookSearchResponse> findBooks2(
            @RequestParam("filter") BookFilterType bookFilterType,
            @RequestParam("value") String value,
            @RequestParam("OperationType")OperationType operationType,
            @RequestParam("Operation_value") String Operation_value)
    {

        return bookService
                .find(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }


}
