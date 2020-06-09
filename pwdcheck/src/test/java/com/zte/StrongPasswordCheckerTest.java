package com.zte;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrongPasswordCheckerTest {
    private StrongPasswordChecker strongPasswordChecker;

    @Before
    public void init(){
        strongPasswordChecker = new StrongPasswordChecker();
    }

    /**
     * 测试密码为空的情况
     */
    @Test
    public void testWhenPasswordIsNull(){
      assertEquals(6,strongPasswordChecker.check(""));
      assertEquals(6,strongPasswordChecker.check(null));

    }

    /**
     * 测试密码长度小于6的情况
     * 分为三种情况
     *
     */
    @Test
    public void testWhenPwdLenLessThanSix(){
        //缺字符类型的数量大于缺少字符的数量
        assertEquals(3,strongPasswordChecker.check("Ab1"));
        assertEquals(2,strongPasswordChecker.check("aaaaa"));
        assertEquals(2,strongPasswordChecker.check("aaaa"));
        assertEquals(3,strongPasswordChecker.check("Abc"));
        assertEquals(1,strongPasswordChecker.check("Abc$@"));
    }

    /**
     * 测试密码长度为6到20的情况
     *
     *
     */
    @Test
    public void testWhenPwdLenBetweenSixAndTwenty(){
        assertEquals(0,strongPasswordChecker.check("Abc422"));

        assertEquals(1,strongPasswordChecker.check("a1aa11"));
        assertEquals(1,strongPasswordChecker.check("A1AA11"));
        assertEquals(1,strongPasswordChecker.check("aAaaAA"));

        assertEquals(1,strongPasswordChecker.check("aA@aA&"));
        assertEquals(2,strongPasswordChecker.check("@a$aa@##"));

        assertEquals(2,strongPasswordChecker.check("@a$aa@##"));
    }


}