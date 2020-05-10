package com.eric.concurrent;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {

    private Integer start = 0;
    private Integer end = 0;

    public ForkJoinExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if (end - start < 100) {
            //小于100时直接返回结果
            int sumResult = 0;
            for (int i = start; i <= end; i++) {
                sumResult += i;
            }
            return sumResult;
        } else {
            //大于一百时进行分割
            int middle = (end + start) / 2;
            ForkJoinExample leftSum = new ForkJoinExample(this.start, middle);
            ForkJoinExample rightSum = new ForkJoinExample(middle, this.end);
            leftSum.fork();
            rightSum.fork();
            return leftSum.join() + rightSum.join();
        }
    }
    static void forkWithDefaultThreadPool(){
        ForkJoinExample sumTask = new ForkJoinExample(1, 999999);
        sumTask.fork();
        System.out.println("result:" + sumTask.join());
    }
    public static void main(String[] args) {
        forkWithDefaultThreadPool();
        forkWithThreadPool();

    }


    static void forkWithThreadPool(){
        // 创建分治任务线程池
        ForkJoinPool fjp =
                new ForkJoinPool(4);
        // 创建分治任务
        Fibonacci fib =
                new Fibonacci(30);
        // 启动分治任务
        Integer result =
                fjp.invoke(fib);
        // 输出结果
        System.out.println(result);
    }
    // 递归任务
    static class Fibonacci extends
            RecursiveTask<Integer>{
        final int n;
        Fibonacci(int n){this.n = n;}
        protected Integer compute(){
            if (n <= 1)
                return n;
            Fibonacci f1 =
                    new Fibonacci(n - 1);
            // 创建子任务
            f1.fork();
            Fibonacci f2 =
                    new Fibonacci(n - 2);
            // 等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }
}