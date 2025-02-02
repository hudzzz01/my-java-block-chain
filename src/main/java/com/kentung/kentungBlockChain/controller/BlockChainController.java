package com.kentung.kentungBlockChain.controller;

import com.kentung.kentungBlockChain.constant.ConstantRoute;
import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.AllBlockResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.MineRequest;
import com.kentung.kentungBlockChain.model.dto.blockChain.MinningResponse;
import com.kentung.kentungBlockChain.service.BlockChainService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ConstantRoute.BLOCKCHAIN_ROUTE)
@AllArgsConstructor
public class BlockChainController {

    BlockChainService blockChainService;

    @PostMapping("/start")
    ResponseEntity<CommondResponse> startBlockCahin() {
        CommondResponse commondResponse = blockChainService.startBlockChain();
        return ResponseEntity.status(HttpStatus.CREATED).body(commondResponse);
    }

    @PostMapping("/mine")
    ResponseEntity<MinningResponse> mineBlock(
            @RequestBody @Valid MineRequest mineRequest
    ) {
        MinningResponse minningResponse = blockChainService.mineNewBlock(mineRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(minningResponse);
    }

    @GetMapping
    ResponseEntity<AllBlockResponse> getAllBlock(){
        AllBlockResponse allBlockResponse = blockChainService.getAllBlock();
        return ResponseEntity.status(HttpStatus.OK).body(allBlockResponse);
    }


}
