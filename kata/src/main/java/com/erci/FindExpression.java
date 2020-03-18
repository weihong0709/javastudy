package com.erci;

public class FindExpression {
    public String findExpression(int i, int v) {
        if (i == v) {
            return String.valueOf(i);
        }
        if (v < i) {
            return handWhenILessThanV(i, v);
        }
        return handWhenIMoreThanV(i, v);

    }

    public String handWhenILessThanV(int i, int v) {
        if (v > i / 2) {
            StringBuilder expression = new StringBuilder("" + i);
            for (int j = 0; j < (i - v); j++) {
                expression.append("-").append(i).append("/").append(i);
            }
            return expression.toString();
        }

        StringBuilder expression = new StringBuilder();
        for (int j = 0; j < v; j++) {
            expression.append(i).append("/").append(i).append("+");
        }
        return expression.substring(0, expression.length() - 1).toString();

    }

    public String handWhenIMoreThanV(int i, int v) {
        if (v % i == 0) {
            for (int j = 0; j < v; j++) {
                int result = (int) Math.pow(i, j);
                if (result == v) {
                    return getMultipyExpression(i, j);

                } else if (result > v) {
                    return getMultipyExpression(i, j - 1) + "+" + handWhenIMoreThanV(i, v - (int) Math.pow(i, j - 1));
                }
            }
        } else {
            int temp = v % i;
            return handWhenIMoreThanV(i, v - temp)+"+"+ handWhenILessThanV(i, temp);
        }
        return null;
    }

    private String getMultipyExpression(int i, int j) {
        StringBuilder expression = new StringBuilder();
        expression.append(i);
        for (int m = 2; m <= j; m++) {
            expression.append("*").append(i);
        }
        return expression.toString();
    }
}
