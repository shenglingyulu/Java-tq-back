package com.fczx.tq.judge.codesandbox.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.fczx.tq.common.ErrorCode;
import com.fczx.tq.exception.BusinessException;
import com.fczx.tq.judge.codesandbox.CodeSandBox;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeRequest;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteCodeBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("远程调用沙箱代码");
        String url="http://localhost:8082/executeSandBox";
        String json= JSONUtil.toJsonStr(executeRequest);
        String response = HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body();
        if (response==null){
            throw new BusinessException(ErrorCode.API_POST_ERROR);
        }
        return JSONUtil.toBean(response, ExecuteCodeResponse.class);
    }
}
