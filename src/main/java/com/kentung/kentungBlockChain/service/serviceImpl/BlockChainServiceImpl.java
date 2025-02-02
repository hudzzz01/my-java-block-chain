package com.kentung.kentungBlockChain.service.serviceImpl;

import com.kentung.kentungBlockChain.model.Block;
import com.kentung.kentungBlockChain.model.BlockChain;
import com.kentung.kentungBlockChain.model.TransactionsListTemoprary;
import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.AllBlockResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.MineRequest;
import com.kentung.kentungBlockChain.model.dto.blockChain.MinningResponse;
import com.kentung.kentungBlockChain.service.BlockChainService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlockChainServiceImpl implements BlockChainService {
//    BlockChain.initGenesisBlock();
//		BlockChain.GetAllBlock();
//
//		TransactionsListTemoprary.addTransaction(new Transaction("0xhudzaifah","0xhanifah",LocalDateTime.now().toString(),100L));
//		BlockChain.minningBlock(TransactionsListTemoprary.getAllTransaction(),"0xsyahid");
//		BlockChain.GetAllBlock();

    @Override
    public CommondResponse startBlockChain() {
        BlockChain.initGenesisBlock();
        return CommondResponse.builder()
                .message("Genesis Block Telah dibuat")
                .build();
    }

    @Override
    public MinningResponse mineNewBlock(MineRequest mineRequest) {
        Block newBlock = BlockChain.minningBlock(TransactionsListTemoprary.getAllTransaction(),mineRequest.getMinerAddress());
        return  MinningResponse.builder()
                .message("Berhasil menambang Block Baru")
                .newBlock(newBlock)
                .build();
    }

    @Override
    public AllBlockResponse getAllBlock() {
        List<Block> blocks = BlockChain.GetAllBlock();
        return AllBlockResponse.builder()
                .blocks(blocks)
                .build();
    }
}
