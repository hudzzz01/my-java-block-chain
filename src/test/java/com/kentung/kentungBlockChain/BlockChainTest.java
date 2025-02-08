package com.kentung.kentungBlockChain;
import com.kentung.kentungBlockChain.model.Block;
import com.kentung.kentungBlockChain.model.BlockChain;
import com.kentung.kentungBlockChain.model.Transaction;
import com.kentung.kentungBlockChain.model.TransactionsListTemoprary;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class BlockChainTest {

    @Test
    void createGenesisBlock() {


        BlockChain blockChain = new BlockChain();

        Transaction transaction = new Transaction(
                "sender",
                "reciver",
                LocalDateTime.now().toString(),
                10000L);

        TransactionsListTemoprary.addTransaction(transaction);
        blockChain.initGenesisBlock();


        Transaction transaction2 = new Transaction(
                "hudza",
                "pipit",
                LocalDateTime.now().toString(),
                10000L);
        TransactionsListTemoprary.addTransaction(transaction2);
        TransactionsListTemoprary.clearAllTransaction();

        blockChain.minningBlock(TransactionsListTemoprary.getAllTransaction(), "0xhudza");


        Transaction transaction3 = new Transaction(
                "garin",
                "fajrul",
                LocalDateTime.now().toString(),
                10000L);
        TransactionsListTemoprary.addTransaction(transaction3);
        TransactionsListTemoprary.clearAllTransaction();

        blockChain.minningBlock(TransactionsListTemoprary.getAllTransaction(), "0xhudza");

        blockChain.validate(BlockChain.GetAllBlock());



    }
}
