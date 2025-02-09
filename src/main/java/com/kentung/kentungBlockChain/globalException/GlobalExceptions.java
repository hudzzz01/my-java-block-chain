package com.kentung.kentungBlockChain.globalException;

import com.kentung.kentungBlockChain.Exception.InvalidBlockException;
import com.kentung.kentungBlockChain.Exception.InvalidNode;
import com.kentung.kentungBlockChain.model.dto.CommondResponse;
import com.kentung.kentungBlockChain.model.dto.blockChain.ValidateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler({InvalidBlockException.class})
    public ResponseEntity<CommondResponse<ValidateResponse>> handleResourceNotFoundException(InvalidBlockException ex) {
        CommondResponse<ValidateResponse> response = CommondResponse.<ValidateResponse>builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({InvalidNode.class})
    public ResponseEntity<CommondResponse<InvalidNode>> handleResourceNotFoundException(InvalidNode ex) {
        CommondResponse<InvalidNode> response = CommondResponse.<InvalidNode>builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
