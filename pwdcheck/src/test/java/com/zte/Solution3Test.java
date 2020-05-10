package com.zte;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class Solution3Test {
    private  Solution3 solution3;
    @BeforeEach
    public void before(){
        solution3 = new Solution3();
    }

    @Test
    void strongPasswordChecker() {
        assertEquals(3, solution3.strongPasswordChecker("aba"));
        System.out.println(Character.isUpperCase('a'));
        System.out.println(Character.isLowerCase('@'));
        System.out.println(Character.isDigit('r'));
        //所有的容器排序，都是从小到大排序
        Queue<Integer> queue= new PriorityQueue<Integer>((a, b) -> a % 3 - b % 3);
        queue.add(5);
        queue.add(4);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}