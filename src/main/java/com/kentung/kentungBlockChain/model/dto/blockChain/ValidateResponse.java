package com.kentung.kentungBlockChain.model.dto.blockChain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateResponse {
    boolean isValidBlockChain;
}
