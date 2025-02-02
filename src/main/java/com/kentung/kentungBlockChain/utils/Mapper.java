package com.kentung.kentungBlockChain.utils;

import com.kentung.kentungBlockChain.model.Transaction;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionRequest;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionResponse;

import java.time.LocalDateTime;
import java.util.List;

public class Mapper {
    public static String mapToStringAlltransactions(List<Transaction> transactions){
        String transactionsString = "";

        int i = 1;
        for(Transaction transaction : transactions){
            transactionsString += transaction.toString();
            if(transactions.size() == i){
                break;
            }
            transactionsString += ",";

        }
        return transactionsString;
    }

    public static Transaction mappTransactionRequestToTransaction(TransactionRequest transactionRequest){

        return new Transaction(
                transactionRequest.getSender(),
                transactionRequest.getReciver(),
                LocalDateTime.now().toString(),
                transactionRequest.getAmount()
        );
    }

    public static TransactionResponse mappTransactionToTransactionResponse(Transaction transaction){

        return new TransactionResponse(
                transaction.getId(),
                transaction.getSender(),
                transaction.getReciver(),
                transaction.getTimeStamp(),
                transaction.getAmount(),
                transaction.getTransactionHash()

        );

    }
}
