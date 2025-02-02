package com.kentung.kentungBlockChain.model.dto.blockChain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MineRequest {
    private String minerAddress;
}
