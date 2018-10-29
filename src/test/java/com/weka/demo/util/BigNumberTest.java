package com.weka.demo.util;

import org.junit.Test;

/**
 * Created by changlu on 10/16/18.
 */
public class BigNumberTest {

    @Test
    public void testAdd(){
        BigNumber a = new BigNumber("11000000000000000000000000000000000000001");
        BigNumber b = new BigNumber("9000000000000000000000000000000000000000000099");
        BigNumber c = a.add(b);
        System.out.println(c.getValue().length());
        System.out.println("9000000000000000000000000000000000000000000099".length());
        System.out.println(c.getValue());
        System.out.println(c.getValue().contains("66666666666666666666666666"));
    }

    @Test
    public void testSubtract(){
        BigNumber a = new BigNumber("111");
        BigNumber b = new BigNumber("99999");
        BigNumber c = a.subtract(b);
        System.out.println(c.getValue());
    }

}
