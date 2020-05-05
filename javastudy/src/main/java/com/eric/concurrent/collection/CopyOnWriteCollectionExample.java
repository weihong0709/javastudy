package com.eric.concurrent.collection;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteCollectionExample {

    public static void main(String[] args) {

    }

    public static void testCopyOnWriteArrayList(){
        CopyOnWriteArrayList<String> copyOnWriteArrayList= new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("11111");
        copyOnWriteArrayList.get(0);
    }

    public static void testCopyOnWriteArraySet(){
        CopyOnWriteArraySet<String> copyOnWriteArraySet= new CopyOnWriteArraySet<>();
        copyOnWriteArraySet.add("11111");
        //copyOnWriteArrayList.get(0);
    }

}
