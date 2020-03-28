package com.eric.concurrent;

import java.util.concurrent.*;

public class FutureExample {
    /**
     * 使用普通的线程池，调用future会阻塞，前面一个返回后，下一个才继续执行
     */
    public static void futureOne() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> futureOne = executorService.submit(() -> {
            Thread.sleep(20000);
            return "thread 1";
        });
        Future<String> futureTwo = executorService.submit(() -> {
            Thread.sleep(30000);
            return "thread 2";
        });
        try {
            System.out.println(futureTwo.get());
            System.out.println(futureOne.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过CompletionService的方式能够缩短阻塞时间，能先获取已完成任务的结果
     *
     */
    public static void futureTwo() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService<String> completionService = new ExecutorCompletionService(executorService);
        Callable<String> futrueOne = () -> {
            Thread.sleep(10000);
            return "thread 1";
        };
        Callable<String> futrueTwo = () -> {
            Thread.sleep(20000);
            return "thread 2";
        };

        Callable<String> futrueThree = () -> {
            Thread.sleep(30000);
            return "thread 3";
        };

        completionService.submit(futrueThree);
        completionService.submit(futrueOne);
        completionService.submit(futrueTwo);

        try {
            for (int i = 0; i < 3; i++) {
                Future<String> temp = completionService.take();
                System.out.println(temp.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /*public static void futureThree() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable runnable = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        CompletableFuture completableFuture = CompletableFuture.runAsync(runnable);
        completableFuture.whenComplete((t)->{
            return "test";
        });
        CompletionService<String> completionService = new ExecutorCompletionService(executorService);

        Callable<String> futrueOne = () -> {
            Thread.sleep(10000);
            return "thread 1";
        };
        Callable<String> futrueTwo = () -> {
            Thread.sleep(20000);
            return "thread 2";
        };

        Callable<String> futrueThree = () -> {
            Thread.sleep(30000);
            return "thread 3";
        };

        completionService.submit(futrueThree);
        completionService.submit(futrueOne);
        completionService.submit(futrueTwo);

        try {
            for (int i = 0; i < 3; i++) {
                Future<String> temp = completionService.take();
                System.out.println(temp.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }*/



}
