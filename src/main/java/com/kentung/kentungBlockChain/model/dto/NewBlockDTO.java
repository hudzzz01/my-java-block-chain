package com.kentung.kentungBlockChain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBlockDTO {
    byte[] hashWithNonce;
    String nonce;
    String difficulty;
    String timeStamp;
    String transactionsString;
}
