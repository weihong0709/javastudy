package com.erci;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindExpressionTest {
    private static FindExpression findExpression;
    @BeforeClass
    public static void init(){
        findExpression = new FindExpression();
    }
    @Test
    public void testWhenVEqualsI() {
        assertEquals("5",findExpression.findExpression(5,5));

    }

    /**
     * i为偶数，且v大于i/2
     */
    @Test
    public void testWhenVLessThan1() {
        assertEquals("6-6/6",findExpression.findExpression(6,5));

    }
    /**
     * i为偶数，且v等于i/2
     */
    @Test
    public void testWhenVLessThanI2() {
        assertEquals("6/6+6/6+6/6",findExpression.findExpression(6,3));

    }
    /**
     * i为偶数，且v小于i/2
     */
    @Test
    public void testWhenVLessThanI3() {
        assertEquals("6/6+6/6",findExpression.findExpression(6,2));

    }
    /**
     * i为偶数，且v等于1
     */
    @Test
    public void testWhenVLessThanI4() {
        assertEquals("6/6",findExpression.findExpression(6,1));

    }
    /**
     * i为奇数，且v大于i/2取整
     */
    @Test
    public void testWhenVLessThanI5() {
        assertEquals("5-5/5-5/5",findExpression.findExpression(5,3));

    }
    /**
     * i为奇数，且v等于i/2取整
     */
    @Test
    public void testWhenVLessThanI6() {
        assertEquals("5/5+5/5",findExpression.findExpression(5,2));

    }
    /**
     * i为奇数，且v小于i/2取整
     */
        @Test
        public void testWhenVLessThanI7() {
            assertEquals("7/7+7/7",findExpression.findExpression(7,2));

    }
    /**
     * i为奇数，且v等于1
     */
    @Test
    public void testWhenVLessThanI8() {
        assertEquals("5/5",findExpression.findExpression(5,1));

    }

}