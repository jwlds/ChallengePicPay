package com.jwdev.ChallengePicPay.controllers;

import com.jwdev.ChallengePicPay.domain.transaction.Transaction;
import com.jwdev.ChallengePicPay.dtos.transactions.TransactionDTO;
import com.jwdev.ChallengePicPay.services.transaction.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@SecurityRequirement(name = "bearer-key")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO payload) throws Exception {
        return new ResponseEntity<Transaction>(this.transactionService.createTransaction(payload), HttpStatus.OK);
    }
}
