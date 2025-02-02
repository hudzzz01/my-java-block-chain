package com.kentung.kentungBlockChain.model;

import com.kentung.kentungBlockChain.utils.Mapper;
import com.kentung.kentungBlockChain.utils.SHA256;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Block {
    private String previousBlockHashWithNonce;
    private String blockHashWithNonce;
    private String blockHash;
    private String nonce;
    private List<Transaction> transactions;
    private Integer index;
    private String difficuty;
    private String timeStampNonce;
    private String timeStamp;

    @Override
    public String toString() {
        return "Block{" +
                "previousBlockHashWithNonce='" + previousBlockHashWithNonce + '\'' +
                ", blockHashWithNonce='" + blockHashWithNonce + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", nonce='" + nonce + '\'' +
                ", transactions=" + transactions +
                ", index=" + index +
                ", difficuty='" + difficuty + '\'' +
                ", timeStampNonce='" + timeStampNonce + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    public String getBlockHashWithNonce() {
        return blockHashWithNonce;
    }

    public Block(String previousBlockHashWithNonce, String blockHashWithNonce, String nonce, List<Transaction> transactions, Integer index, String difficuty, String timeStampNonce) {
        this.previousBlockHashWithNonce = previousBlockHashWithNonce;
        this.blockHashWithNonce = blockHashWithNonce;
        this.nonce = nonce;
        this.transactions = transactions;
        this.index = index;
        this.difficuty = difficuty;
        this.timeStampNonce = timeStampNonce;
        this.timeStamp = LocalDateTime.now().toString();

        String transactionsString =  Mapper.mapToStringAlltransactions(transactions);

        this.blockHash = SHA256.generateSHA256Hash(
                this.previousBlockHashWithNonce +
                        this.blockHash +
                        this.nonce +
                        transactionsString +
                        this.nonce +
                        this.index +
                        this.previousBlockHashWithNonce +
                        this.timeStampNonce+
                        this.timeStamp +
                        this.index
        );




    }
}
