package com.kentung.kentungBlockChain.model.dto.blockChain;

import com.kentung.kentungBlockChain.model.Block;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MinningResponse {
    private String message;
    private Block newBlock;
}
