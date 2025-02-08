package com.kentung.kentungBlockChain.service;

import com.kentung.kentungBlockChain.Exception.InvalidNode;
import com.kentung.kentungBlockChain.model.Node;
import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.AllBlockResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.MineRequest;
import com.kentung.kentungBlockChain.model.dto.blockChain.MinningResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.ValidateResponse;

import java.util.List;
import java.util.UUID;

public interface BlockChainService {
    CommondResponse startBlockChain();
    MinningResponse mineNewBlock(MineRequest mineRequest);
    AllBlockResponse getAllBlock();
    ValidateResponse validateBlockChain();
    boolean sycnBlockChain();


}
