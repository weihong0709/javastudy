package com.eric;



import java.util.ArrayList;
import java.util.Stack;

public class MathEvaluatorNew {
    private char CHAR_ZERO='0';
    private char CHAR_EMPTY='0';
    private char CHAR_LEFT_PARENTHESIS = '(';
    private char CHAR_RIGHT_PARENTHESIS = ')';


    public int calculate(String expression) {
        int numCount=0;
        int number = 0;
        Stack<Object> stack = new Stack<>();
        for (int i=expression.length()-1;i>=0;i--){
            char currentChar =  expression.charAt(i);
            if (Character.isDigit(currentChar)){
                number=(int)Math.pow(10,numCount)*(int)(currentChar-CHAR_ZERO)+number;
                numCount++;
            }else if (currentChar!=CHAR_EMPTY){
                if (numCount!=0){
                    stack.push(number);
                    number=0;
                    numCount=0;
                }
                if (currentChar==CHAR_LEFT_PARENTHESIS){
                    int res = evaluateExpr(stack);
                    stack.pop();
                    stack.push(res);

                }else{
                    stack.push(currentChar);
                }
            }

        }
        if (numCount!=0){
            stack.push(number);
        }
        return evaluateExpr(stack);

    }
    private int evaluateExpr(Stack<Object> stack){
        int res = 0;
        if (!stack.isEmpty()){
            res=(int)stack.pop();
        }
        while (!stack.empty()&&(char)stack.peek()!=CHAR_RIGHT_PARENTHESIS){
            char currentChar = (char)stack.pop();
            if (currentChar=='+'){
                res+=(int)stack.pop();
            }else {
                res -= (int)stack.pop();
            }

        }
        return res;
    }

}
