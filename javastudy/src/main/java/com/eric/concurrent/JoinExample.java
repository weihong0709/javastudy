package com.eric.concurrent;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class JoinExample {
    /**
     * join方法的作用：让当前线程等待线程执行完后再在调用join方法的地方继续往下执行
     *
     */
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0;i<10;i++){
            Thread thread = new Thread(()->{
                try {
                    System.out.println("begin thread:");
                    Thread.sleep(10000);
                    System.out.println("end thread:");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads.add(thread);
            thread.start();
        }

    }
}
