package com.eric;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathEvaluatorNewTest {


    /**
     * 单个数字
     */
    @Test
    public void testOnlyNumber() {
        assertEquals(123,new MathEvaluatorNew().calculate("123"), 0.01);
    }

    /**
     * 单个数字带负号
     */
    @Test
    public void testNegative() {
        assertEquals(-123,new MathEvaluatorNew().calculate("-123"), 0.01);
    }

    /**
     * 开头是括号
     */
    @Test
    public void testBeginWithParentheses() {
        assertEquals(-123,new MathEvaluatorNew().calculate("1+1"), 0.01);
    }
    /**
     * 简单相加
     */
    @Test
    public void testSimpleAdd() {
        assertEquals(2,new MathEvaluatorNew().calculate("1+1"), 0.01);
    }

    /**
     * 加一个负数
     */
    @Test
    public void testAddNegative() {
        assertEquals(2,new MathEvaluatorNew().calculate("1+-1"), 0.01);
    }
}