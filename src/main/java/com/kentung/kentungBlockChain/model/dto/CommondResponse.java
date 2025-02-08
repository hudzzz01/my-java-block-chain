package com.kentung.kentungBlockChain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommondResponse <T> {
    int statusCode;
    String message;
    T data;
}
