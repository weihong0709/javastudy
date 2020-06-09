package com.eric.generic;

import java.util.ArrayList;
import java.util.List;

/*
* List、List<Object>、List<?>的区别
*
**/
public class ListNoGeneric {
    public static void main(String[] args) {
        List a1 = new ArrayList();
        a1.add(new Object());
        a1.add(new Integer(11));
        a1.add(new String("hello a1"));

        List<Object> a2 = a1;
        a2.add(new Object());
        a2.add(new Integer(222));
        a2.add(new String("hello a2"));

        List<Integer> a3 = a1;
        //a3.add(new Object());
        a3.add(new Integer(333));
        //a3.add(new String("hello a3"));

        List<?> a4 = a1;
        a4.remove(0);
        a4.clear();
        //不允许添加任何元素
        //a4.add(new Object());

    }
}
