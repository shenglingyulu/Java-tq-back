package com.fczx.tq.judge.codesandbox.impl;
import com.fczx.tq.judge.codesandbox.model.JudgeInfo;
import com.fczx.tq.judge.codesandbox.CodeSandBox;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeRequest;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeResponse;
import com.fczx.tq.model.enums.JudgeInfoMessageEnum;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Slf4j
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        List<String> inputList = executeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse=new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        JudgeInfo judgeInfo=new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
