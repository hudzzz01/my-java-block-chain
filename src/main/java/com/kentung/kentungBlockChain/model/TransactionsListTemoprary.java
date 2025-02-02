package com.kentung.kentungBlockChain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TransactionsListTemoprary {
    private static List<Transaction> transactions = new ArrayList<>();

    public static void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public static List<Transaction> getAllTransaction (){
        return transactions;
    }

    public static void clearAllTransaction(){
        transactions = new ArrayList<>();
    }

}
