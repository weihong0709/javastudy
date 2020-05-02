package com.eric.common;

import java.util.Objects;

public class HashCodeAndEqualsMethod {
    public static void main(String[] args) {
        Object a = null;
        Object b = null;
        Objects.equals(a,b);
    }
    /**
     * hashCode方法默认是用对象的地址计算得到的一个值，String覆写了hashCode方法
     * 推荐使用Objects的equals方法
     */
}
