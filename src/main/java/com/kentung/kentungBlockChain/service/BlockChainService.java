package com.kentung.kentungBlockChain.service;

import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.AllBlockResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.MineRequest;
import com.kentung.kentungBlockChain.model.dto.blockChain.MinningResponse;

public interface BlockChainService {
    CommondResponse startBlockChain();
    MinningResponse mineNewBlock(MineRequest mineRequest);
    AllBlockResponse getAllBlock();
}
