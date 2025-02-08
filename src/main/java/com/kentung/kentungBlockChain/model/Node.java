package com.kentung.kentungBlockChain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {
    private int index;
    private String id;
    private String url;
}
