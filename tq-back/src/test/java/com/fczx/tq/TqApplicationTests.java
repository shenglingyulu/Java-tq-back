package com.fczx.tq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TqApplicationTests {

    @Test
    void contextLoads() {

                int a,b,c;
                for(int i=101;i<1000;i++) {
                    a=i%10;
                    b=i/10%10;
                    c=i/100;
                    if(a*a*a+b*b*b+c*c*c==i)
                        System.out.println(i);
                }
            }




}
