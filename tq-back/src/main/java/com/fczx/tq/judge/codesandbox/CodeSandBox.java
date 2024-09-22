package com.fczx.tq.judge.codesandbox;

import com.fczx.tq.judge.codesandbox.model.ExecuteCodeRequest;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandBox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest );
}
