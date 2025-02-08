package com.kentung.kentungBlockChain.utils;

import com.kentung.kentungBlockChain.model.Block;
import com.kentung.kentungBlockChain.model.Transaction;
import com.kentung.kentungBlockChain.model.dto.BlockRequest;
import com.kentung.kentungBlockChain.model.dto.BlockResponse;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionRequest;
import com.kentung.kentungBlockChain.model.dto.transaction.TransactionResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

public class Mapper {
    public static String mapToStringAlltransactions(List<Transaction> transactions){
        StringBuilder transactionsString = new StringBuilder();

        for(Transaction transaction : transactions){
            transactionsString.append(transaction.toString());
        }
        return transactionsString.toString();
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

    public static BlockResponse mapToResponse(Block block){

        String hashWithNonceString = Base64.encodeBase64String(block.getBlockHashWithNonce());
        String previousBlockHashWithNonceString = Base64.encodeBase64String(block.getPreviousBlockHashWithNonce());

        return BlockResponse.builder()
                .blockHashWithNonce(hashWithNonceString)
                .previousBlockHashWithNonce(previousBlockHashWithNonceString)
                .index(block.getIndex())
                .blockHash(block.getBlockHash())
                .nonce(block.getNonce())
                .transactions(block.getTransactions())
                .difficuty(block.getDifficuty())
                .timeStampNonce(block.getTimeStampNonce())
                .timeStamp(block.getTimeStamp())
                .build();
    }

    public static Block mapToBlock(BlockRequest blockRequest){

        byte[] hashWithNonceString = Base64.decodeBase64(blockRequest.getBlockHashWithNonce());
        byte[] previousBlockHashWithNonceString = Base64.decodeBase64(blockRequest.getPreviousBlockHashWithNonce());

        return new Block(
                previousBlockHashWithNonceString,
                hashWithNonceString,
                blockRequest.getNonce(),
                blockRequest.getTransactions(),
                blockRequest.getIndex(),
                blockRequest.getDifficuty(),
                blockRequest.getTimeStampNonce());
    }

    public static Block mapToBlock(BlockResponse blockResponse){

        byte[] hashWithNonceString = Base64.decodeBase64(blockResponse.getBlockHashWithNonce());
        byte[] previousBlockHashWithNonceString = Base64.decodeBase64(blockResponse.getPreviousBlockHashWithNonce());

        return new Block(
                previousBlockHashWithNonceString,
                hashWithNonceString,
                blockResponse.getNonce(),
                blockResponse.getTransactions(),
                blockResponse.getIndex(),
                blockResponse.getDifficuty(),
                blockResponse.getTimeStampNonce());
    }

}
