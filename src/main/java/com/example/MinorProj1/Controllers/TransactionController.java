package com.example.MinorProj1.Controllers;


import com.example.MinorProj1.Services.TransactionService;
import com.example.MinorProj1.exceptions.TxnServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService txnService;

    // SId - 10 - Piyush
    // SId - 2 - Rohan

    @PostMapping("/transaction/issue")
    public String issueTxn(@RequestParam("studentId") int studentId,
                           @RequestParam("bookId") int bookId) throws TxnServiceException, InterruptedException {
        return txnService.issuetxn(studentId, bookId);
    }



}
