package com.kentung.kentungBlockChain.model.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private UUID id;
    private String sender;
    private String reciver;
    private String timeStamp;
    private Long amount;
    private String transactionHash;
}
