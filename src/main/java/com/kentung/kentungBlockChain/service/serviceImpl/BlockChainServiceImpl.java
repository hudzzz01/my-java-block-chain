package com.kentung.kentungBlockChain.service.serviceImpl;

import com.kentung.kentungBlockChain.Exception.InvalidNode;
import com.kentung.kentungBlockChain.model.*;
import com.kentung.kentungBlockChain.model.dto.BlockRequest;
import com.kentung.kentungBlockChain.model.dto.BlockResponse;
import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.*;
import com.kentung.kentungBlockChain.service.BlockChainService;
import com.kentung.kentungBlockChain.service.NodeService;
import com.kentung.kentungBlockChain.service.RestCLientService;
import com.kentung.kentungBlockChain.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlockChainServiceImpl implements BlockChainService {

    private final NodeService nodeService;

    private final RestClient restClient;


    @Override
    public CommondResponse startBlockChain() {
        BlockChain.initGenesisBlock();
        Block block = BlockChain.GetAllBlock().get(0);

        BlockResponse blockResponse = Mapper.mapToResponse(block);

        return CommondResponse.builder()
                .message("Genesis Block Telah dibuat")
                .data(blockResponse)
                .build();
    }

    @Override
    public MinningResponse mineNewBlock(MineRequest mineRequest) {
        System.out.println("Prosses mining sedang berjalan");
        Block newBlock = BlockChain.minningBlock(TransactionsListTemoprary.getAllTransaction(), mineRequest.getMinerAddress());
        TransactionsListTemoprary.clearAllTransaction();

        BlockResponse blockResponse = Mapper.mapToResponse(newBlock);

        return MinningResponse.builder()
                .message("Berhasil menambang Block Baru")
                .newBlock(blockResponse)
                .build();
    }

    @Override
    public AllBlockResponse getAllBlock() {
        List<Block> blocks = BlockChain.GetAllBlock();
        System.out.println();
        System.out.println("All block");

        List<BlockResponse> blockResponses = blocks.stream().map(Mapper::mapToResponse).toList();

        return AllBlockResponse.builder()
                .blocks(blockResponses)
                .build();
    }

    @Override
    public ValidateResponse validateBlockChain() {
        BlockChain.validate(BlockChain.GetAllBlock());
        return ValidateResponse.builder()
                .isValidBlockChain(true)
                .build();
    }

    public boolean sycnBlockChain() {

        List<Node> nodes = nodeService.readAllNode();
        if(nodes.isEmpty()) throw new InvalidNode("nodes is empety");

        String host = "http://localhost:9090";

        List<Block> blocks = null;

        int max = 0;
        for(Node node : nodes){
            List<Block> temp = getBlock(node.getUrl());
            if(temp.size() > max){
                max = temp.size();
                blocks = temp;
            }
        }


        //todo if validate an max of insert thats block to his block chain
        ArrayList <Block> blockArrayList = new ArrayList<>();


        //todo add block terbaik

        blockArrayList.addAll(blocks);

        BlockChain.setBlocks(blockArrayList);


        return true;

//        restClient.get()
//                .uri("/posts")
//                .retrieve()
//                .body(new ParameterizedTypeReference<List<BlockRequest>>() {});
    }

    private List<Block> getBlock(String host) {
        GetAllBlockResponse response = restClient.get()
                .uri(host + "/api/v1/blockChain")
                .retrieve()
                .body(GetAllBlockResponse.class);

        List<Block> blocks = response.getBlocks().stream().map(
                Mapper::mapToBlock
        ).toList();


        System.out.println(blocks);
        System.out.println(blocks.size());

        try{
            //todo validate new block from another node
            BlockChain.validate(blocks);
        } catch (Exception e) {
            System.out.println("invalid block in node " + host);
            return List.of(new Block());
        }
        return blocks;
    }


}
