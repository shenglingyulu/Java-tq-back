package com.fczx.tq.judge.codesandbox;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeRequest;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox{
    private final CodeSandBox codeSandBox;
    public CodeSandBoxProxy(CodeSandBox codeSandBox){
        this.codeSandBox=codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        log.info("沙箱代码请求"+executeRequest.toString());
        ExecuteCodeResponse executeCodeResponse=codeSandBox.executeCode(executeRequest);
        log.info("沙箱代码响应"+executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
