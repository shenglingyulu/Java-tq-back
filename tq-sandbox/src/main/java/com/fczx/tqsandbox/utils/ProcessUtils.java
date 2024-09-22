package com.fczx.tqsandbox.utils;

import cn.hutool.core.date.StopWatch;
import com.fczx.tqsandbox.model.ExecuteMessage;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess,String tsName){
        ExecuteMessage executeMessage=new ExecuteMessage();
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            //等待程序获取响应码
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            //正常退出
            if(exitValue==0){
                System.out.println(tsName+"成功");
                // 分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                List<String> outputStrList = new ArrayList<>();
                // 逐行读取
                String compileOutputLine;
                while((compileOutputLine= bufferedReader.readLine())!=null)
                {
                    outputStrList.add(compileOutputLine);
                }
                executeMessage.setMessage(StringUtils.join(outputStrList,'\n'));
            }else{//异常退出
                System.out.println(tsName+"失败"+exitValue);
                //正确信息
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                List<String> outputStrList = new ArrayList<>();
                // 逐行读取
                String compileOutputLine;
                while((compileOutputLine= bufferedReader.readLine())!=null)
                {
                    outputStrList.add(compileOutputLine);
                }
                executeMessage.setMessage(StringUtils.join(outputStrList,'\n'));
                //错误信息
                BufferedReader errorbufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                List<String> errotOutputStrList = new ArrayList<>();
                // 逐行读取
                String errorCompileOutputLine;
                while((errorCompileOutputLine= errorbufferedReader.readLine())!=null)
                {
                    errotOutputStrList.add(errorCompileOutputLine);
                }
                executeMessage.setErrorMessage(StringUtils.join(errotOutputStrList,'\n'));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return executeMessage;
    }
}
