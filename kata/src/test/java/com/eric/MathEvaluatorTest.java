package com.eric;

import org.junit.Test;

public class MathEvaluatorTest {

/*
    *//**
     * 没有运算符
     *//*
    @Test
    public void testOnlyNumber() {
        assertEquals(123,new MathEvaluator().calculate("123"), 0.01);
    }

    *//**
     * 有一个运算符，为负号，且在第一个位置
     *//*
    @Test
    public void testNegative() {
        assertEquals(-123,new MathEvaluator().calculate("-123"), 0.01);
    }

    *//**
     * 有一个运算符，为减法
     *//*
    @Test
    public void testSimpleSub() {
        assertEquals(1,new MathEvaluator().calculate("2-1"), 0.01);
    }
    *//**
     * 有一个运算符，为加法
     *//*
    @Test
    public void testSimpleAdd() {
        assertEquals(2,new MathEvaluator().calculate("1+1"), 0.01);
    }

    *//**
     * 有一个运算符，为除法
     *//*
    @Test
    public void testSimpleDivision() {
        assertEquals(2,new MathEvaluator().calculate("2/1"), 0.01);
    }
    *//**
     * 有一个运算符，为乘法
     *//*
    @Test
    public void testSimpleMultiply() {
        assertEquals(4,new MathEvaluator().calculate("2*2"), 0.01);
    }

    *//**
     * 有两个运算符，第一个为加，第二个为减号
     *//*
    @Test
    public void testAddNegative() {
        assertEquals(0,new MathEvaluator().calculate("1+-1"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为加，第二个为减号
     *//*
    @Test
    public void testNegativeAdd() {
        assertEquals(0,new MathEvaluator().calculate("-1+1"), 0.01);
    }

    *//**
     * 有两个运算符，第一个为减法，第二个为减号
     *//*
    @Test
    public void testSubNegative() {
        assertEquals(2,new MathEvaluator().calculate("1--1"), 0.01);
    }

    *//**
     * 有两个运算符，第一个为除法，第二个为减号
     *//*
    @Test
    public void testDivisionNegative() {
        assertEquals(-2,new MathEvaluator().calculate("2/-1"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为乘法，第二个为减号
     *//*
    @Test
    public void testMultiplyNegative() {
        assertEquals(-2,new MathEvaluator().calculate("2*-1"), 0.01);
    }

    *//**
     * 有两个运算符，第一个为加或者减法，第二个为加法或者减法
     *//*
    @Test
    public void testADDAndAdd() {
        assertEquals(3,new MathEvaluator().calculate("1+1+1"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为加或者减法，第二个为乘法或者除法
     *//*
    @Test
    public void testAddAndSub() {
        assertEquals(0,new MathEvaluator().calculate("1+1-2"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为加或者减法，第二个为乘法或者除法
     *//*
    @Test
    public void testAddAndMultipy() {
        assertEquals(3,new MathEvaluator().calculate("1+1*2"), 0.01);
    }

    *//**
     * 有两个运算符，第一个为减法，第二个为加法
     *//*
    @Test
    public void testSubAndAdd() {
        assertEquals(2,new MathEvaluator().calculate("1-1+2"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为减法，第二个为减法
     *//*
    @Test
    public void testSubAndSub() {
        assertEquals(-2,new MathEvaluator().calculate("1-1-2"), 0.01);
    }

    *//**
     * 有两个运算符，第一个为加减，第二个为乘法
     *//*
    @Test
    public void testSubAndMultiply() {
        assertEquals(-1,new MathEvaluator().calculate("1-1*2"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为加减，第二个为除法
     *//*
    @Test
    public void testSubAndDivision() {
        assertEquals(-1,new MathEvaluator().calculate("1-4/2"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为乘法或者除法，第二个为加法或者减法，即前面的优先级高，后面的优先级低
     *//*
    @Test
    public void testMultiplyAndSub() {
        assertEquals(2,new MathEvaluator().calculate("1*4-2"), 0.01);
    }
    *//**
     * 有两个运算符，第一个为乘法或者除法，第二个也为乘法或者除法，即前面的优先级高，后面的优先级低
     *//*
    @Test
    public void testMultiplyAndDivision() {
        assertEquals(2,new MathEvaluator().calculate("1*4/2"), 0.01);
    }

    *//**
     * 第一个运算符为加法或者减法，第二个运算符为括号，且括号后无其他运算
     *//*
    @Test
    public void testSubAndParentheses() {
        assertEquals(-3,new MathEvaluator().calculate("1-(5-1)"), 0.01);
    }

    *//**
     * 第一个运算符为加法或者减法，第二个运算符为括号，括号后包含其他运算
     *//*
    @Test
    public void testSubAndParenthesesAndOtherOperation() {
        assertEquals(1,new MathEvaluator().calculate("1-(5-1)+4"), 0.01);
    }
    *//**
     * 第一个运算符为加法或者减法，第二个运算符为括号，且括号后无其他运算
     *//*
    @Test
    public void testMultiplyAndParentheses() {
        assertEquals(4,new MathEvaluator().calculate("1*(5-1)"), 0.01);
    }
    *//**
     * 第一个运算符为乘法或者除法，第二个运算符为括号，括号后包含其他运算
     *//*
    @Test
    public void testMultiplyAndParenthesesAndOtherOperation() {
        assertEquals(8,new MathEvaluator().calculate("1*(5-1)+4"), 0.01);
    }

    *//**
     * 第一个运算符为括号，括号里面为单个正数
     *//*
    @Test
    public void testBeginWithParenthesesAndPositive() {
        assertEquals(4,new MathEvaluator().calculate("(4)"), 0.01);
    }
    *//**
     * 第一个运算符为括号，括号里面为单个负数
     *//*
    @Test
    public void testBeginWithParenthesesAndNegative() {
        assertEquals(-4,new MathEvaluator().calculate("(-4)"), 0.01);
    }
    *//**
     * 第一个运算符为括号，括号里面为表达式，且括号后无内容
     *//*
    @Test
    public void testBeginWithParenthesesAndExpression() {
        assertEquals(-1,new MathEvaluator().calculate("(-4+3)"), 0.01);
    }

    *//**
     * 第一个运算符为括号，括号里面为表达式，且括号后有计算内容
     *//*
    @Test
    public void testBeginWithParenthesesAndOtherEXpression() {
        assertEquals(-2,new MathEvaluator().calculate("(-4+3)*2"), 0.01);
    }

    @Test public void testExpression() {
        assertEquals(new MathEvaluator().calculate("2 /2+3 * 4.75- -6"), 21.25, 0.01);
    }

    @Test public void testSimple() {
        assertEquals(new MathEvaluator().calculate("12* 123"), 1476d, 0.01);
    }

    @Test public void testComplex() {
        assertEquals(new MathEvaluator().calculate("2 / (2 + 3) * 4.33 - -6"), 7.732, 0.01);
    }
    @Test
    public void testWhenParenthesesAfterNegative(){
        assertEquals(new MathEvaluator().calculate("-(-5 + 2)"), 3, 0.01);
    }
    @Test
    public void testWhenParenthesesAfterNegativeComplex(){
        //嵌套一层括号的情况
        assertEquals(new MathEvaluator().calculate("-((-5 + 2))"), 3, 0.01);
        assertEquals(new MathEvaluator().calculate("-(-(-5 + 2))"), -3, 0.01);
        assertEquals(new MathEvaluator().calculate("1-(-(-5 + 2))"), -3, 0.01);
    }*/

}