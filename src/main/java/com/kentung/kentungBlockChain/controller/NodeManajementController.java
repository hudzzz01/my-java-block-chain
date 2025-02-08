package com.kentung.kentungBlockChain.controller;

import com.kentung.kentungBlockChain.constant.ConstantRoute;
import com.kentung.kentungBlockChain.model.Node;
import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.service.BlockChainService;
import com.kentung.kentungBlockChain.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ConstantRoute.NODE_ROUTE)
@AllArgsConstructor

public class NodeManajementController {

    NodeService nodeService;


    @PostMapping(path = "/addNode")
    ResponseEntity<CommondResponse> addNode(@RequestBody String address) {
        Node node = nodeService.addNode(address);
        CommondResponse commondResponse = CommondResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Berhasil menambahkan node")
                .data(node)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commondResponse);
    }

    @DeleteMapping(path = "/removeNode")
    ResponseEntity<CommondResponse> removeNode(@RequestBody int index) {
        Node node = nodeService.removeNode(index);
        CommondResponse commondResponse = CommondResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Berhasil menghapus node")
                .data(node)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commondResponse);
    }

    @GetMapping(path = "/readAllNode")
    ResponseEntity<CommondResponse<List<Node>>> readAllNode() {
        List<Node> nodes = nodeService.readAllNode();
        CommondResponse<List<Node>> commondResponse = CommondResponse.<List<Node>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Berhasil membaca semua node")
                .data(nodes)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commondResponse);
    }


}
