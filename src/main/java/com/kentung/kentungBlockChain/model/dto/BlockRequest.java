package com.kentung.kentungBlockChain.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlockRequest {
    private String previousBlockHashWithNonce;
    private String blockHashWithNonce;
    private String blockHash;
    private String nonce;
    //    private List<Transaction> transactions;
    private String transactions;
    private String index;
    private String difficuty;
    private String timeStampNonce;
    private String timeStamp;
}
