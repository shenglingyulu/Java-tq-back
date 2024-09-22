package com.fczx.tqsandbox;


import com.fczx.tqsandbox.model.ExecuteCodeRequest;
import com.fczx.tqsandbox.model.ExecuteCodeResponse;

public interface CodeSandBox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest );
}
