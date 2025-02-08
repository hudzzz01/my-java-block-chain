package com.kentung.kentungBlockChain.model.dto.blockChain;

import com.kentung.kentungBlockChain.model.Block;
import com.kentung.kentungBlockChain.model.dto.BlockResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllBlockResponse {
    List<BlockResponse> blocks;
}
