package com.eric;

import java.util.ArrayList;

public class MathEvaluatorNew {

    public double calculate(String expression) {
        expression = expression.trim();
        OperatorInfo operatorInfo = getFirstOperator(expression);
        //只有单个数字，不带负号
        if (operatorInfo == null) {
            return Double.parseDouble(expression);
        }
        //只有单个数字,带符号
        if (operatorInfo.operator == Operator.SUB&&operatorInfo.pos==0) {
            return Double.parseDouble(expression);
        }
        //第一个运算符为加法
        if (operatorInfo.operator == Operator.ADD) {
            String beginExpression = null;
            String endExpression = null;
            double numberOne = 0;
            beginExpression = expression.substring(0, operatorInfo.getPos()).trim();
            endExpression = expression.substring(operatorInfo.getPos() + 1).trim();
            numberOne = Double.parseDouble(beginExpression);
            OperatorInfo next = getFirstOperator(endExpression);
            if (next==null){
                return calculate(operatorInfo.operator,numberOne,Double.parseDouble(endExpression));
            }else {

            }

        }

        //开头是括号
        if (operatorInfo.operator == Operator.PARENTHESES) {
            String beginExpression = null;
            String endExpression = null;
            int rightParentheses = expression.indexOf(")");
            if (expression.length() == rightParentheses + 1) {
                return calculate(expression.substring(1, expression.length() - 1));
            } else {
                beginExpression = expression.substring(1, rightParentheses).trim();
                endExpression = expression.substring(rightParentheses + 1, expression.length());
                String finalExpression = calculate(beginExpression) + endExpression;
                return calculate(finalExpression);
            }
        }

        return 0;

    }

    private boolean isContainsOperator(String expression) {
        return expression.contains("+") || expression.contains("-") || expression.contains("*") || expression.contains("/");
    }

    private double calculate(Operator operator, double one, double two) {
        double result = 0;
        if (operator == Operator.ADD) {
            result = one + two;
        }
        if (operator == Operator.SUB) {
            result = one - two;
        }
        if (operator == Operator.MULTIPY) {
            result = one * two;
        }
        if (operator == Operator.DIVISION) {
            result = one / two;
        }
        return result;
    }

    private OperatorInfo getFirstOperator(String expression) {
        expression = expression.trim();
        OperatorInfo operatorInfoAdd = this.getAddOperator(expression);
        ArrayList<OperatorInfo> operatorInfos = new ArrayList<>();
        if (operatorInfoAdd!=null){
            operatorInfos.add(operatorInfoAdd);
        }
        OperatorInfo operatorInfoSub = this.getSubOperator(expression);
        if (operatorInfoSub!=null){
            operatorInfos.add(operatorInfoSub);
        }
        OperatorInfo operatorInfoDivision = this.getDivisionOperator(expression);
        if (operatorInfoDivision!=null){
            operatorInfos.add(operatorInfoDivision);
        }
        OperatorInfo operatorInfoMultipy = this.getMultipyOperator(expression);
        if (operatorInfoMultipy!=null){
            operatorInfos.add(operatorInfoMultipy);
        }
        OperatorInfo operatorInfoParentheses = this.getParenthesesOperator(expression);
        if (operatorInfoParentheses!=null){
            operatorInfos.add(operatorInfoParentheses);
        }
        if (operatorInfos.size()==0){
            return null;
        }
        return operatorInfos.stream().sorted().findFirst().get();
    }
    private OperatorInfo getAddOperator(String expression){
        int index = expression.indexOf("+");
        if (index > -1) {
            return new OperatorInfo(Operator.ADD,index);
        }
        return  null;
    }
    private OperatorInfo getSubOperator(String expression){
        int index = expression.indexOf("-");
        if (index > -1) {
            return new OperatorInfo(Operator.SUB,index);
        }
        return  null;
    }
    private OperatorInfo getMultipyOperator(String expression){
        int index = expression.indexOf("*");
        if (index > -1) {
            return new OperatorInfo(Operator.MULTIPY,index);
        }
        return  null;
    }
    private OperatorInfo getDivisionOperator(String expression){
        int index = expression.indexOf("/");
        if (index > -1) {
            return new OperatorInfo(Operator.DIVISION,index);
        }
        return  null;
    }
    private OperatorInfo getParenthesesOperator(String expression){
        int index = expression.indexOf("(");
        if (index > -1) {
            return new OperatorInfo(Operator.PARENTHESES,index);
        }
        return  null;
    }

    enum Operator {
        ADD, SUB, DIVISION, MULTIPY, PARENTHESES
    }

    class OperatorInfo {
        private Operator operator;
        private int pos;
        public OperatorInfo(Operator operator,int pos){
            this.operator = operator;
            this.pos = pos;
        }

        public Operator getOperator() {
            return operator;
        }

        public void setOperator(Operator operator) {
            this.operator = operator;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
    }

}
