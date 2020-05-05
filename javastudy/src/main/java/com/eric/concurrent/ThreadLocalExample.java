package com.eric.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreadLocalExample {

    public static void main(String[] args) {

    }

    /**
     * 线程安全的SimpleDateFormat
     */
    static class SafeDateFormat {
        // 定义 ThreadLocal 变量
        static final ThreadLocal<DateFormat>
                tl=ThreadLocal.withInitial(
                ()-> new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss"));

        static DateFormat get(){
            return tl.get();
        }
    }

}
