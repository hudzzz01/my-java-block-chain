package com.kentung.kentungBlockChain.service.serviceImpl;

import com.kentung.kentungBlockChain.model.BlockChain;
import com.kentung.kentungBlockChain.model.Node;
import com.kentung.kentungBlockChain.service.NodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {

    @Override
    public Node addNode(String address) {
        return BlockChain.addNode(address);
    }

    @Override
    public Node removeNode(int index) {
        return BlockChain.removeNode(index);


    }

    @Override
    public List<Node> readAllNode() {
        return BlockChain.readAllNode();

    }


}
