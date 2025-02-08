package com.kentung.kentungBlockChain.service;

import com.kentung.kentungBlockChain.model.Node;

import java.util.List;

public interface NodeService {

    Node addNode(String address);
    Node removeNode(int index);
    List<Node> readAllNode();

}
