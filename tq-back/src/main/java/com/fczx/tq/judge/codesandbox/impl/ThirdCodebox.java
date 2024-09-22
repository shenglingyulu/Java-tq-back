package com.fczx.tq.judge.codesandbox.impl;

import com.fczx.tq.judge.codesandbox.CodeSandBox;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeRequest;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThirdCodebox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("第三方沙箱调用");
        return null;
    }
}
