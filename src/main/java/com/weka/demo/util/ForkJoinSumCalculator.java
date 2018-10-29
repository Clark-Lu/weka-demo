package com.weka.demo.util;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * Created by changlu on 10/22/18.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println(ForkJoinSumCalculator.forkJoinSum(10));
    }

    private final long[] numbers;

    private final int start;

    private final int end;

    public static final int THRESHOLD = 10;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum = sum + numbers[i];
            }
            return sum;
        } else {
            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }

    public static long forkJoinSum(long n) throws ExecutionException, InterruptedException, TimeoutException {
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask task = new ForkJoinSumCalculator(numbers);
        return (long) new ForkJoinPool().submit(task).get(1L, TimeUnit.SECONDS);
    }
}
