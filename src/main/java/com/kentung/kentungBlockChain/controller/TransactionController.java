package com.kentung.kentungBlockChain.controller;


import com.kentung.kentungBlockChain.constant.ConstantRoute;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionRequest;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionResponse;
import com.kentung.kentungBlockChain.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ConstantRoute.TRANSACTION_ROUTE)
@AllArgsConstructor
public class TransactionController {

    TransactionService transactionService;

    @PostMapping
    ResponseEntity<TransactionResponse> addTransaction(
            @RequestBody @Valid TransactionRequest transactionRequest){
       TransactionResponse transactionResponse = transactionService.addTransactions(transactionRequest);
       return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

    @GetMapping
    ResponseEntity<List<TransactionResponse>> getAllTransaction(){
        List<TransactionResponse> transactionResponses = transactionService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponses);
    }

    @DeleteMapping
    ResponseEntity<String> deleteAllTransaction(){
        transactionService.deleteAllTransaction();
        return ResponseEntity.status(HttpStatus.CREATED).body("Berhasil mengosongkan transaksi");
    }

}
