package com.fczx.tqsandbox.model;

import lombok.Data;

@Data
public class ExecuteMessage {
    private String message;
    private String errorMessage;
    private Integer exitValue;
    private Long time;
    private Long memory;
}
