package com.kentung.kentungBlockChain.model;

import com.kentung.kentungBlockChain.utils.SHA256;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private UUID id;
    private String sender;
    private String reciver;
    private String timeStamp;
    private Long amount;
    private String transactionHash;


    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", reciver='" + reciver + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", amount=" + amount +
                ", transactionHash='" + transactionHash + '\'' +
                '}';
    }

    public Transaction(String sender, String reciver, String timeStamp, Long amount) {
        this.id = UUID.randomUUID();
        this.sender = sender;
        this.reciver = reciver;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.transactionHash =      SHA256.generateSHA256Hash(this.sender + this.reciver + this.timeStamp + this.amount.toString());
    }
}
