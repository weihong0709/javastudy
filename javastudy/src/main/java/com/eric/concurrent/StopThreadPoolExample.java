package com.eric.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class StopThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("线程被中断");
            }
            System.out.println("执行完成");
        });
        /**
         * 终止线程池有两种方法
         * （1）shutdown()：是一种很保守的关闭线程池的方法。线程池执行 shutdown() 后，就会拒绝接收新的任务，
         *      但是会等待线程池中正在执行的任务和已经进入阻塞队列的任务都执行完之后才最终关闭线程池
         * （2）shutdownNow()：线程池执行 shutdownNow() 后，会拒绝接收新的任务，同时还会中断线程池中正在执行的任务，已经进入阻塞队列的任务也被剥夺了执行的机会，
         *     不过这些被剥夺执行机会的任务会作为 shutdownNow() 方法的返回值返回。因为 shutdownNow() 方法会中断正在执行的线程，
         *     所以提交到线程池的任务，如果需要优雅地结束，就需要正确地处理线程中断
         */
        //executorService.shutdown();

        List<Runnable> runnableList =  executorService.shutdownNow();

    }
}
