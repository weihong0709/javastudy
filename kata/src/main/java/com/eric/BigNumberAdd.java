package com.eric;

public class BigNumberAdd {
    private char[] bigLength;
    private char[] smallLength;
    private int result[];

    public String add(String a, String b) {
        convert(a,b);
        add();
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

    private void add() {

        for (int i = 0; i <bigLength.length ; i++) {
            result[i] = (bigLength[i]-'0');
        }
        for (int i = 0; i <smallLength.length ; i++) {
           result[i]+=(smallLength[i]-'0');
        }
    }



    private void convert(String a,String b) {
        if (a.length()>=b.length()){
            bigLength  = new StringBuilder(a).reverse().toString().toCharArray();
            smallLength = new StringBuilder(b).reverse().toString().toCharArray();
        }else {
            bigLength  = new StringBuilder(b).reverse().toString().toCharArray();
            smallLength = new StringBuilder(a).reverse().toString().toCharArray();
        }

        result = new int[bigLength.length+1];

    }
}
