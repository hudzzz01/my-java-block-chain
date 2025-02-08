package com.kentung.kentungBlockChain.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BlockTemporaryForValidation {
    public static List<Block> blocks;
}
