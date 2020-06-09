package com.eric;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberAddTest {
    @Test
    public void testAdd(){
        assertEquals("6",new BigNumberAdd().add("3","3"));
    }

}