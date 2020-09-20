package com.fact.dto;

import lombok.Data;

@Data
public class ResponseMessage {
    private String message;
    private int code;

    public ResponseMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
