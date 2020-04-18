package com.erci;

import org.junit.Test;

import static org.junit.Assert.*;

public class StrCompareTest {

    /**
     * 测试两个字符串都为空或者一方为空的情况
     */
    @Test
    public  void testGivenNullThanReturnZero(){
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("",""));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("10",""));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("","10"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc",""));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("","abc"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("","00"));
    }
    /**
     * 测试普通数字串相等
     */
    @Test
    public  void testGivenNumberWhenEqualThanReturnZero(){
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("10","10"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("0","0"));
    }
    /**
     * 测试普通数字串大于的情况
     */
    @Test
    public  void testGivenNumberWhenMoreThanThenReturnMoreThan(){
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("20","10"));
    }
    /**
     * 测试普通数字串小于的情况
     */
    @Test
    public  void testGivenNumberWhenLessThanThenReturnLessThan(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("20","40"));
    }
    /**
     * 测试普通数字串相等，且前面有0的情况
     * 0的数量分为等于、大于、小于三种
     */
    @Test
    public  void testGivenNumberEqualAndPrefixContainsZero(){
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("0","0"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("00","00"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("0020","0020"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("020","0020"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("0020","020"));

    }
    /**
     * 测试普通数字串大于，且前面有0的情况
     * 0的数量分为等于、大于、小于三种
     */
    @Test
    public  void testGivenNumberMoreThanAndPrefixContainsZero(){
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("30","0"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("00","0"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("30","00"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("0030","0020"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("0030","020"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("0030","20"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("30","020"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("30","0020"));

    }
    /**
     * 测试普通数字串小于，且前面有0的情况
     * 0的数量分为等于、大于、小于三种
     */
    @Test
    public  void testGivenNumberLessThanAndPrefixContainsZero(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0","00"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("00","0040"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0020","0040"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0020","040"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0020","40"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("020","0040"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("20","0040"));
    }

    /**
     * 测试数字串包含转义字符的情况
     * 转义字符在头部，在中间、在末尾的情况
     */
    @Test
    public  void testGivenNumberContainsEscapes(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0020","\\A"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\A","0020"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0020","4\\A1"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("4\\A1","0020"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("0020","11\\A"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("11\\A","0020"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("234\\A","2405"));
    }

    /**
     * 测试数字串包含多个转义字符的情况
     */
    @Test
    public  void testGivenNumberContainsMoreThanOneEscapes(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("20","\\A\\a"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\A\\a","0020"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("20","4\\A14\\a\\b4"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("4\\A14\\a\\b4","0020"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("234\\A\\A","24115"));

    }


    /**
     * 测试普通字符串相等
     */
    @Test
    public  void testGivenStrWhenEqualThanReturnZero(){
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("abc","abc"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("ABC","ABC"));
    }
    /**
     * 测试普通字符串大于的情况
     */
    @Test
    public  void testGivenStrWhenMoreThanThenReturnMoreThan(){
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc","Abc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abcd","abc"));
    }
    /**
     * 测试普通字符串小于的情况
     */
    @Test
    public  void testGivenStrWhenLessThanThenReturnLessThan(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("ABC","abc"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("abc","abcd"));
    }

    /**
     * 测试字符串包含转义字符的情况
     * 转义字符在头部，在中间、在末尾的情况
     */
    @Test
    public  void testGivenStringContainsEscapes(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\2ab","abc"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\2ab","\\2abc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\2abc","\\2ab"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\22ab","\\23ab"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\23ab","\\22ab"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\223ab","\\234ab"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\234ab","\\223ab"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("ab\\44ab","ab\\33ab"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abd\\44","abd\\33"));

    }

    /**
     * 测试字符串包含多个转义字符的情况
     */
    @Test
    public  void testGivenStringContainsMoreThanOneEscapes(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\234\\34ab","\\234\\34abc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\234\\34abc","\\234\\34ab"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("abc\\234\\34\\efg","abc\\234\\35efg"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc\\234\\35efg","abc\\234\\34\\efg"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("abc\\234\\34\\22","abc\\234\\34\\33\\22"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc\\234\\34\\33\\22","abc\\234\\34\\22"));
    }

    /**
     * 子串即有数字串，又有字符串，不包含转义字符的情况
     */
    @Test
    public  void testGivenStringAndNumber(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("234","abc"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("234abc","abc"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("234abc","234abe"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("131abc","234abe"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("abc233","abc233"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("abc233abc","abc233abc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc233","234abc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc234","abc233"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("abc233abd","abc233abc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("000h","00h"));
    }


    /**
     * 子串即有数字串，又有字符串，至少有一个有转义字符,结果相等的情况
     */
    @Test
    public  void testGivenComplexAndConstainsEscapesWhenEquals(){
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("\\23455","\\23455"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("\\Aabc","\\Aabc"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("\\Aabc214\\A","\\Aabc214\\A"));
        assertEquals(StrCompareConstants.RESULT_EQ,StrCompare.compare("\\Aabc214\\Aabc\\12","\\Aabc214\\Aabc\\12"));


    }
    /**
     * 子串即有数字串，又有字符串，至少有一个有转义字符,结果大于的情况
     */
    @Test
    public  void testGivenComplexAndConstainsEscapesWhenMoreThan(){
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\23456","\\23455"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\Aabd","\\Aabc"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\Aabc214\\a","\\Aabc214\\A"));
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("\\Aabc215\\Aabc","\\Aabc214\\Aabc"));

    }
    /**
     * 子串即有数字串，又有字符串，至少有一个有转义字符,结果小于的情况
     */
    @Test
    public  void testGivenComplexAndConstainsEscapesWhenLessThan(){
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\23453","\\23455"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\Aabc","\\Abbc"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\Aabc214\\A","\\Aabc217\\A"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\Aabc214\\Aabc","\\Aabc214\\Aabd"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\A21","24887"));
        assertEquals(StrCompareConstants.RESULT_LESSTHAN,StrCompare.compare("\\A21","24887\\ABC"));

    }

    /**
     * 给定的字符串格式不正确的情况
     */
    @Test
    public  void testGivenInvalidStrThanReturnFailure(){
        assertEquals(StrCompareConstants.RESULT_INVALID,StrCompare.compare("ddd\\","222"));
        assertEquals(StrCompareConstants.RESULT_INVALID,StrCompare.compare("?ddd","222"));
        assertEquals(StrCompareConstants.RESULT_INVALID,StrCompare.compare("\\\\ddd","222"));
        assertEquals(StrCompareConstants.RESULT_INVALID,StrCompare.compare("ddd","\\\222"));
        assertEquals(StrCompareConstants.RESULT_INVALID,StrCompare.compare("ddd\\sdsd;dd","222"));

    }

    /**
     * 测试数字超过int范围的情况
     */
    @Test
    public  void testGivenBigNumberThenReturnSuccess(){
        assertEquals(StrCompareConstants.RESULT_MORETHAN,StrCompare.compare("429496777444422233322334444433333\\A295bb","429496777444422233322334444433333\\A295aa"));


    }
}