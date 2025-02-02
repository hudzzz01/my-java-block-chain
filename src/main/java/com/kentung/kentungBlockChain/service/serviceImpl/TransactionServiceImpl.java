package com.kentung.kentungBlockChain.service.serviceImpl;

import com.kentung.kentungBlockChain.model.Transaction;
import com.kentung.kentungBlockChain.model.TransactionsListTemoprary;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionRequest;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionResponse;
import com.kentung.kentungBlockChain.service.TransactionService;
import com.kentung.kentungBlockChain.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public TransactionResponse addTransactions(TransactionRequest transactionRequest) {
        Transaction transaction = Mapper.mappTransactionRequestToTransaction(transactionRequest);
        TransactionsListTemoprary.addTransaction(transaction);
        return Mapper.mappTransactionToTransactionResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = TransactionsListTemoprary.getAllTransaction();
        return transactions.stream().map(Mapper::mappTransactionToTransactionResponse).toList();


    }

    @Override
    public void deleteAllTransaction() {
        TransactionsListTemoprary.clearAllTransaction();
    }
}
