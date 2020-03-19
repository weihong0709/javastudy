package com.erci;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindExpressionTest {
    private static FindExpression findExpression;

    @BeforeClass
    public static void init() {
        findExpression = new FindExpression();
    }

    @Test
    public void testWhenVEqualsI() {
        assertEquals("5", findExpression.findExpression(5, 5));

    }

    /**
     * i为偶数，V小于I，且v大于i/2
     */
    @Test
    public void testWhenVLessThan1() {
        assertEquals("6-6/6", findExpression.findExpression(6, 5));

    }

    /**
     * i为偶数，V小于I，且v等于i/2
     */
    @Test
    public void testWhenVLessThanI2() {
        assertEquals("6/6+6/6+6/6", findExpression.findExpression(6, 3));

    }

    /**
     * i为偶数，且v小于i/2
     */
    @Test
    public void testWhenVLessThanI3() {
        assertEquals("6/6+6/6", findExpression.findExpression(6, 2));

    }

    /**
     * i为偶数，且v等于I
     */
    @Test
    public void testWhenVLessThanI4() {
        assertEquals("6/6", findExpression.findExpression(6, 1));

    }

    /**
     * i为奇数，且v大于i/2取整，但小于I
     */
    @Test
    public void testWhenVLessThanI5() {
        assertEquals("5-5/5-5/5", findExpression.findExpression(5, 3));

    }

    /**
     * i为奇数，且v等于i/2取整
     */
    @Test
    public void testWhenVLessThanI6() {
        assertEquals("5/5+5/5", findExpression.findExpression(5, 2));

    }

    /**
     * i为奇数，且v小于i/2取整
     */
    @Test
    public void testWhenVLessThanI7() {
        assertEquals("7/7+7/7", findExpression.findExpression(7, 2));

    }

    /**
     * i为奇数，且v等于I
     */
    @Test
    public void testWhenVLessThanI8() {
        assertEquals("5", findExpression.findExpression(5, 5));

    }

    /**
     * V大于I，且v等于I的整数倍
     */
    @Test
    public void testWhenVMoreThanI1() {
        assertEquals("3*3", findExpression.findExpression(3, 9));

    }

    /**
     * V大于I，且v等于I的整数倍
     */
    @Test
    public void testWhenVMoreThanI2() {
        assertEquals("3*3*3+3*3", findExpression.findExpression(3, 36));

    }

    /**
     * V大于I，且v不等于I的整数倍，剩余的余数小于I的二分之一
     */
    @Test
    public void testWhenVMoreThanI3() {
        assertEquals("3*3*3+3*3+3/3", findExpression.findExpression(3, 37));

    }

    /**
     * V大于I，且v不等于I的整数倍，剩余的余数大于I的二分之一
     */
    @Test
    public void testWhenVMoreThan4() {
        assertEquals("3*3*3+3*3+3-3/3", findExpression.findExpression(3, 38));

    }

    /**
     * V大于I，且v不等于I的整数倍，小于I的2倍
     */
    @Test
    public void testWhenVMoreThanI5() {
        assertEquals("3+3/3", findExpression.findExpression(3, 4));

    }

    /**
     * V大于I，且v不等于I的整数倍
     */
    @Test
    public void testWhenVMoreThanI6() {
        assertEquals("7*7+7*7+7/7", findExpression.findExpression(7, 99));

    }

}