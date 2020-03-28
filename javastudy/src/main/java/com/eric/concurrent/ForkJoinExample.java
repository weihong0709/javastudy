package com.eric.concurrent;


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

    public static void main(String[] args) {
        ForkJoinExample sumTask = new ForkJoinExample(1, 999999);
        sumTask.fork();
        System.out.print("result:" + sumTask.join());
    }
}