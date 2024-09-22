package com.fczx.tq.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {
 private List<String> outputList;
 private String message;
 private JudgeInfo judgeInfo;
 private Integer status;
}
