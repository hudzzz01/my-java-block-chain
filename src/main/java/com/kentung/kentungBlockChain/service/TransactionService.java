package com.kentung.kentungBlockChain.service;

import com.kentung.kentungBlockChain.model.dto.transaction.TransactionRequest;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse addTransactions(TransactionRequest transactionRequest);
    List<TransactionResponse> getAllTransactions();
    void deleteAllTransaction();
}
