package com.eric.concurrent;

public class SyncronizeExample {
    Object lock = new Object();
    public void syncronizeBlock(){
        int i = 10;
        synchronized (lock){
        try {
            //进入syncronize块后会获得对象监视器，wait后会释放监视器，唤醒后会再获得监视器
            lock.wait(100);
            System.out.println("唤醒后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
}
