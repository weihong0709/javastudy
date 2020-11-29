package com.eric;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathEvaluatorNewTest {
    @Test
    public void testWhenOnlyOneNumber(){
    assertEquals(2,new MathEvaluatorNew().calculate("2"));
    }
    @Test
    public void testSimpleAdd(){
        assertEquals(3,new MathEvaluatorNew().calculate("2+1"));
    }

}