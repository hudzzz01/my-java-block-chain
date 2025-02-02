package com.kentung.kentungBlockChain.model.dto.blockChain;

import com.kentung.kentungBlockChain.model.Block;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllBlockResponse {
    List<Block> blocks;
}
