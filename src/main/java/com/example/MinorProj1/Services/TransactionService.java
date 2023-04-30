package com.example.MinorProj1.Services;

import com.example.MinorProj1.exceptions.TxnServiceException;
import com.example.MinorProj1.models.Book;
import com.example.MinorProj1.models.Student;
import com.example.MinorProj1.models.Transaction;
import com.example.MinorProj1.models.TransactionType;
import com.example.MinorProj1.repositories.TransactionRepository;
import com.example.MinorProj1.requests.BookFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

     /*
     Student is a valid entity
     Book is Present and is available
     Create a transaction and then save it in the transaction table
     Make the book unavailable when someone has it on their name
      */



    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public String issuetxn( int studentId, int bookid) throws TxnServiceException {

        Student student = studentService.findByStundetId(studentId);

        try{
            if(student==null){
                throw  new TxnServiceException("Student is not enrolled in the library");
            }
        } catch (TxnServiceException e ){
            return "Error" +e.getMessage();
        }


//        Book book = bookService.findByid(bookid);
//        if(book==null){
//            throw new TxnServiceException("Book currently not available");
//        }

        List<Book> books = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookid));
        if(books == null || books.size() !=1 || books.get(0).getStudent() !=null){
            throw new TxnServiceException("Book not present in the library");
        }

        Transaction transaction = Transaction.builder().externalTxnId(UUID.randomUUID().toString()).transactionType(TransactionType.ISSUE)
                .payment(books.get(0).getCost()).my_book(books.get(0)).my_student(student).build();

        transactionRepository.save(transaction);

        books.get(0).setStudent(student);
        bookService.create(books.get(0));

        return transaction.getExternalTxnId();

    }







}
