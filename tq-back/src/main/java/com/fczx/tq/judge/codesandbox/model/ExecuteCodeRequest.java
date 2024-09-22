package com.fczx.tq.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Scanner;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {
    private List<String> inputList;
    private String code;
    private String language;

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=2;
        while(n>=k) {
            if(n==k) {
                System.out.println(k);
                break;
            }else if (n%k==0) {
                System.out.println(k);
                n=n/k;
            }else {
                k++;
            }
        }


    }
}
