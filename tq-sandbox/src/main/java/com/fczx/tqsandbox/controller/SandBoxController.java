package com.fczx.tqsandbox.controller;

import com.fczx.tqsandbox.JavaNativeSandBox;
import com.fczx.tqsandbox.model.ExecuteCodeRequest;
import com.fczx.tqsandbox.model.ExecuteCodeResponse;
import com.fczx.tqsandbox.model.ExecuteMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class SandBoxController {
    @Resource
    private JavaNativeSandBox javaNativeSandBox;
    @PostMapping("/executeSandBox")
    public ExecuteCodeResponse execteSandBox(@RequestBody ExecuteCodeRequest executeCodeRequest){
        if (executeCodeRequest==null){
            throw new RuntimeException("参数为空");
        }
        ExecuteCodeResponse executeCodeResponse = javaNativeSandBox.executeCode(executeCodeRequest);
        System.out.println("判题信息"+executeCodeResponse.getMessage());
        return executeCodeResponse;
    }
}
