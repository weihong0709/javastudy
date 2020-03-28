package com.eric.concurrent;

import org.junit.jupiter.api.Test;


public class ForkJoinExampleTest {

    @Test
    public void compute() {
        ForkJoinExample sumTask = new ForkJoinExample(1, 999999);
        sumTask.fork();
        System.out.print("result:" + sumTask.join());
    }
}