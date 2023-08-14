package com.example.lmsproject.controllers;

import com.example.lmsproject.exceptions.TxnServiceException;
import com.example.lmsproject.services.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles HTTP requests related to book transactions
 * such as issuing and returning books.
 */
@RestController
public class TransactionController {

    // Service for interacting with transaction related data.
    @Autowired
    TxnService txnService;

    /**
     * Endpoint to issue a book to a student.
     *
     * @param studentId The ID of the student to whom the book is to be issued.
     * @param bookId The ID of the book to be issued.
     * @return A message indicating the outcome of the transaction.
     * @throws TxnServiceException if there's an issue with the transaction.
     * @throws InterruptedException if the thread execution is interrupted.
     */
    @PostMapping("/transaction/issue")
    public String issueTxn(@RequestParam("studentId") int studentId,
                           @RequestParam("bookId") int bookId) throws TxnServiceException, InterruptedException {
        return txnService.issueTxn(studentId, bookId);
    }

    /**
     * Endpoint to return a book from a student.
     *
     * @param studentId The ID of the student returning the book.
     * @param bookId The ID of the book being returned.
     * @return A message indicating the outcome of the transaction.
     * @throws TxnServiceException if there's an issue with the transaction.
     * @throws InterruptedException if the thread execution is interrupted.
     */
    @PostMapping("/transaction/return")
    public String returnTxn(@RequestParam("studentId") int studentId,
                            @RequestParam("bookId") int bookId) throws TxnServiceException, InterruptedException {
        return txnService.returnTxn(studentId, bookId);
    }
}
