package com.fczx.tq.judge.codesandbox;

import com.fczx.tq.judge.codesandbox.impl.ExampleCodeSandBox;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeRequest;
import com.fczx.tq.judge.codesandbox.model.ExecuteCodeResponse;
import com.fczx.tq.model.enums.QuestionSubmitLanguageEnum;
import com.fczx.tq.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodeSandBoxTest {
    @Value("${sandbox.type:example}")
    private String sandboxtype;
    @Test
       void ExecuteSandBox(){
           CodeSandBox codeSandBox=new ExampleCodeSandBox();
           String code="hello";
           List<String> inputList= Arrays.asList("2","2");
           String langua= QuestionSubmitLanguageEnum.JAVA.getValue();
           ExecuteCodeRequest executeCodeRequest=ExecuteCodeRequest.builder()
                   .code(code)
                   .language(langua)
                   .inputList(inputList)
                   .build();
           ExecuteCodeResponse executeCodeResponse=codeSandBox.executeCode(executeCodeRequest);
       }
    @Test
    void ExecuteSandBoxFactory(){
        CodeSandBox codeSandBox=CodeSandBoxFactory.Instance(sandboxtype);
        codeSandBox= new CodeSandBoxProxy(codeSandBox);
        String code="hello";
        List<String> inputList= Arrays.asList("2","2");
        String langua= QuestionSubmitLanguageEnum.JAVA.getValue();
        ExecuteCodeRequest executeCodeRequest=ExecuteCodeRequest.builder()
                .code(code)
                .language(langua)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandBox.executeCode(executeCodeRequest);
    }
}