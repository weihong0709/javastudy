package com.eric;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberMultipyTest {
    @Test
    public void testSimpleMutiply(){
        assertEquals("6",new BigNumberMultiply().multipy("2","3"));
        assertEquals("0",new BigNumberMultiply().multipy("0","3"));
        assertEquals("10000",new BigNumberMultiply().multipy("100","100"));
    }

}