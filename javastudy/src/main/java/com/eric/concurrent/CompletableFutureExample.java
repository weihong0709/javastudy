package com.eric.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    public static void main(String[] args) {

       handSerialTask();
       handOrTask();

    }
    static void  sleep(int t, TimeUnit u){
        try {
            u.sleep(t);

        }catch(InterruptedException e){}
    }
    /*
    * 描述串行关系
    * */
    public static void handSerialTask(){

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            return "设计";
        }).thenApply((s)->{
            return s+"编码";

        }).thenApply((s)->{
            return s+"测试完成";

        });
        System.out.println(completableFuture.join());
    }


    /*
     * 描述 AND 汇聚关系
     * */
    public static void handCombineTask(){


        // 任务 1：洗水壶 -> 烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(()->{
                    System.out.println("T1: 洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1: 烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
// 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T2: 洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2: 洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2: 拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return " 龙井 ";
                });
// 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf)->{
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });
// 等待任务 3 执行结果
        System.out.println(f3.join());
    }


    /*
     * 描述串行关系
     * */
    public static void handOrTask(){

        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = new Random(5).nextInt();
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = new Random(5).nextInt();
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);

        System.out.println(f3.join());
    }

}
