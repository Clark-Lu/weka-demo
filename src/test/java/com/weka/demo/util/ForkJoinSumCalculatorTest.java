package com.weka.demo.util;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by changlu on 10/22/18.
 */
public class ForkJoinSumCalculatorTest {

    @Test
    public void testForkJoinSum() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println(ForkJoinSumCalculator.forkJoinSum(20));
    }

}
