package com.fczx.tq.judge.codesandbox;

import com.fczx.tq.judge.codesandbox.impl.ExampleCodeSandBox;
import com.fczx.tq.judge.codesandbox.impl.RemoteCodeBox;
import com.fczx.tq.judge.codesandbox.impl.ThirdCodebox;

public class CodeSandBoxFactory {
    public static CodeSandBox Instance(String type){
        switch (type){
            case "example" : return new ExampleCodeSandBox();
            case "remote"  : return new RemoteCodeBox();
            case "third"   : return new ThirdCodebox();
            default:         return new ExampleCodeSandBox();
        }
    }
}
