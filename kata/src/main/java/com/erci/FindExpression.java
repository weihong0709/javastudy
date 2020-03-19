package com.erci;

public class FindExpression {
    public String findExpression(int i, int v) {
        if (v < i) {
            return handWhenILessThanV(i, v);
        }
        return handWhenIMoreThanV(i, v);
    }

    public String handWhenILessThanV(int i, int v) {
        if (isVMoreThanIHalf(i, v)) {
            return getDivisionExpression(i, v);
        }
        return getAddExpression(i, v);

    }

    private String getDivisionExpression(int i, int v) {
        StringBuilder expression = new StringBuilder("" + i);
        for (int j = 0; j < (i - v); j++) {
            expression.append("-").append(i).append("/").append(i);
        }
        return expression.toString();
    }

    private String getAddExpression(int i, int v) {
        StringBuilder expression = new StringBuilder();
        for (int j = 0; j < v; j++) {
            expression.append(i).append("/").append(i).append("+");
        }
        return expression.substring(0, expression.length() - 1).toString();
    }


    private boolean isVMoreThanIHalf(int i, int v) {
        return v > i / 2;
    }

    public String handWhenIMoreThanV(int i, int v) {
        if (v % i == 0) {
            return handWhenDivisible(i, v);
        }
        return handWhenNotDivisible(i, v);
    }

    private String handWhenNotDivisible(int i, int v) {
        int left = v % i;
        return handWhenIMoreThanV(i, v - left) + "+" + handWhenILessThanV(i, left);
    }

    private String handWhenDivisible(int i, int v) {
        for (int j = 0; j < v; j++) {
            int result = (int) Math.pow(i, j);
            if (result == v) {
                return getMultiplyExpression(i, j);

            } else if (result > v) {
                return getMultiplyExpression(i, j - 1) + "+" + handWhenIMoreThanV(i, v - (int) Math.pow(i, j - 1));
            }
        }
        return null;
    }


    private String getMultiplyExpression(int i, int j) {
        StringBuilder expression = new StringBuilder("" + i);
        for (int m = 2; m <= j; m++) {
            expression.append("*").append(i);
        }
        return expression.toString();
    }
}
