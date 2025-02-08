package com.kentung.kentungBlockChain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockResponse {
    private String previousBlockHashWithNonce;
    private String blockHashWithNonce;
    private String blockHash;
    private String nonce;
    private String transactions;
    private String index;
    private String difficuty;
    private String timeStampNonce;
    private String timeStamp;
}
