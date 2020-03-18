package com.erci;

public class FindExpression {
    public String findExpression(int i,int v){
        if (i==v){
            return String.valueOf(i);
        }
        if (v<i){
            return handWhenILessThanV(i,v);
        }
        handWhenIMoreThanV(i,v);

        return  null;
    }

    public String handWhenILessThanV(int i,int v){
        if (v>i/2){
            StringBuilder expression = new StringBuilder(""+i);
            for (int j=0;j<(i-v);j++){
                expression.append("-").append(i).append("/").append(i);
            }
            return expression.toString();
        }

        StringBuilder expression = new StringBuilder();
        for (int j=0;j<v;j++){
            expression.append(i).append("/").append(i).append("+");
        }
        return expression.substring(0,expression.length()-1).toString();

    }
    public String handWhenIMoreThanV(int i,int v){
        if (v>i/2){
            StringBuilder expression = new StringBuilder(""+i);
            for (int j=0;j<(i-v);j++){
                expression.append("-").append(i).append("/").append(i);
            }
            return expression.toString();
        }

        StringBuilder expression = new StringBuilder();
        for (int j=0;j<v;j++){
            expression.append(i).append("/").append(i).append("+");
        }
        return expression.substring(0,expression.length()-1).toString();

    }
}
