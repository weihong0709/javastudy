package com.eric;

public class BigNumberMultiply {

    private char[] one;
    private char[] two;
    private int result[];

    public String multipy(String a, String b) {
        convert(a,b);
        multipy();
        carryOver();
        return convertToString();

    }

    private String convertToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
        }
        String result = builder.reverse().toString();
        if (result.startsWith("0")){
            result = result.substring(1);
        }
        return result;

    }

    private void carryOver() {
        for (int i = 0; i < result.length-1; i++) {
             int left = result[i]%10;
             result[i+1] +=result[i]/10;
             result[i] = left;
        }

    }

    private void multipy() {
        for (int i = 0; i <one.length ; i++) {
            for (int j = 0; j <two.length ; j++) {
                result[i+j]+=simpleMultiply(one[i],two[j]);
            }

        }
    }

    private int simpleMultiply(char c, char c1) {
        return (c-'0')*(c1-'0');
    }

    private void convert(String a,String b) {
        one = new StringBuilder(a).reverse().toString().toCharArray();
        two = new StringBuilder(b).reverse().toString().toCharArray();
        result = new int[one.length + two.length];

    }

}
