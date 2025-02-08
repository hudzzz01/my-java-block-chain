package com.kentung.kentungBlockChain.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Setter
@Getter
public class Block {
    private byte[] previousBlockHashWithNonce;
    private byte[] blockHashWithNonce;
    private String blockHash;
    private String nonce;
//    private List<Transaction> transactions;
    private String transactions;
    private String index;
    private String difficuty;
    private String timeStampNonce;
    private String timeStamp;


    public Block(byte[] previousBlockHashWithNonce, byte[] blockHashWithNonce, String nonce, String transactions, String index, String difficuty, String timeStampNonce) {
        this.previousBlockHashWithNonce = previousBlockHashWithNonce;
        this.blockHashWithNonce = blockHashWithNonce;
        this.nonce = nonce;
        this.transactions = transactions;
        this.index = index;
        this.difficuty = difficuty;
        this.timeStampNonce = timeStampNonce;
        this.timeStamp = LocalDateTime.now().toString();

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(previousBlockHashWithNonce, block.previousBlockHashWithNonce) && Objects.equals(blockHashWithNonce, block.blockHashWithNonce) && Objects.equals(blockHash, block.blockHash) && Objects.equals(nonce, block.nonce) && Objects.equals(transactions, block.transactions) && Objects.equals(index, block.index) && Objects.equals(difficuty, block.difficuty) && Objects.equals(timeStampNonce, block.timeStampNonce) && Objects.equals(timeStamp, block.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousBlockHashWithNonce, blockHashWithNonce, blockHash, nonce, transactions, index, difficuty, timeStampNonce, timeStamp);
    }

    @Override
    public String toString() {
        return "Block{" +
                "previousBlockHashWithNonce='" + previousBlockHashWithNonce + '\'' +
                ", blockHashWithNonce='" + blockHashWithNonce + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", nonce='" + nonce + '\'' +
                ", transactions='" + transactions + '\'' +
                ", index='" + index + '\'' +
                ", difficuty='" + difficuty + '\'' +
                ", timeStampNonce='" + timeStampNonce + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    public String validateBlock(byte[] previoesBlockHasWithNonce){
//        String transactionsString = mapToStringAlltransactions(transactions);
        if(!Arrays.equals(this.previousBlockHashWithNonce, previoesBlockHasWithNonce)) throw new RuntimeException("invalid block");

        String dataForNewBlock = new String(previoesBlockHasWithNonce) + transactions + index + nonce;

        byte[] hash = DigestUtils.sha256(dataForNewBlock);

        String hashWIthNonce = new String(hash);

        System.out.println(hashWIthNonce);
        return null;
    }

}
