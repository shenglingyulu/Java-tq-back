package com.fczx.tq.judge.codesandbox.model;

import lombok.Data;

@Data
public class ExecuteMessage {
   private Integer exitValue;
   private String message;
   private String errorMessage;
   private Long memory;
   private Long time;
}
