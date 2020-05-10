package com.eric.concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

public class ThreadPoolExample {
    /**
     * 固定大小线程池
     */
    public  void fixThreadPoolExample(){
        Executors.newFixedThreadPool(2);
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 3,
                TimeUnit.SECONDS,new ArrayBlockingQueue(10),new ThreadPoolExecutor.AbortPolicy());

    }
}
