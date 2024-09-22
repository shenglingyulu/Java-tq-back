package com.fczx.tqsandbox;

import com.fczx.tqsandbox.model.ExecuteCodeRequest;
import com.fczx.tqsandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

@Component
public class JavaNativeSandBox extends JavaSandBoxTemplate {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        return super.executeCode(executeRequest);
    }
}
