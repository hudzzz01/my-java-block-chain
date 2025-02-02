package com.kentung.kentungBlockChain.model.dto.transaction;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    @NotEmpty
    private String sender;
    @NotEmpty
    private String reciver;
    @NotEmpty
    private Long amount;
}
