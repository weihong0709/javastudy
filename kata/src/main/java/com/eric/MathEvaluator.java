package com.eric;

<<<<<<< HEAD
public class MathEvaluator {

    public double evaluator(String expression){
        return 0;
    }
=======


import java.util.ArrayList;

public class MathEvaluator {

    public double calculate(String expression) {
        expression = expression.trim();
        OperatorInfo operatorInfo = getFirstOperator(expression);
        //只有单个数字，不带负号¡
        if (operatorInfo == null) {
            return Double.parseDouble(expression);
        }
        OperatorInfo next = getFirstOperator(expression.substring(operatorInfo.pos+1));
        boolean isFirstCharIsNegative = isFirstCharIsNegative(operatorInfo);
        if (isFirstCharIsNegative) {
            //单个负数
            if (next==null){
                return Double.parseDouble(expression);
            }else{
                operatorInfo = next;
                operatorInfo.setPos(operatorInfo.pos+1);
                next = getFirstOperator(expression.substring(operatorInfo.pos+1));
            }
        }
        //第一个运算符为加法或者减法
        if (operatorInfo.operator == Operator.ADD||operatorInfo.operator == Operator.SUB) {

            String beginExpression = expression.substring(0, operatorInfo.getPos()).trim();
            String endExpression = expression.substring(operatorInfo.getPos() + 1).trim();
            double numberOne = Double.parseDouble(beginExpression);
            if (next==null){
                return calculate(operatorInfo.operator,numberOne,Double.parseDouble(endExpression));
            }else {
                if (isFirstCharIsNegative(next)){
                    return calculate(operatorInfo.operator,numberOne,Double.parseDouble(endExpression));
                }else if(next.operator==Operator.ADD|| next.operator==Operator.SUB){
                    double numberTwo = Double.parseDouble(endExpression.substring(0,next.pos)) ;
                    String newExpression = calculate(operatorInfo.operator,numberOne,numberTwo)+endExpression.substring(next.pos);
                    return calculate(newExpression);
                }else if(next.operator==Operator.DIVISION|| next.operator==Operator.MULTIPY){
                    return calculate(operatorInfo.operator,numberOne,calculate(endExpression));
                }else if(next.operator==Operator.PARENTHESES){
                    int rightParentheses = endExpression.indexOf(")");
                    if (endExpression.length() == rightParentheses + 1) {
                        return calculate(operatorInfo.operator,numberOne,calculate(endExpression.substring(1, endExpression.length() - 1)));
                    } else {
                        String parenthesesExpression = endExpression.substring(1, rightParentheses).trim();
                        String parenthesesRightExpression = endExpression.substring(rightParentheses + 1, endExpression.length());
                        String finalExpression = expression.substring(0,operatorInfo.pos+1) + calculate(parenthesesExpression)+parenthesesRightExpression;
                    return calculate(finalExpression);
                }

                }
            }
        }
        //第一个运算符为乘法或者除法
        if (operatorInfo.operator == Operator.DIVISION||operatorInfo.operator==Operator.MULTIPY) {
            String beginExpression = null;
            String endExpression = null;
            double numberOne = 0;
            beginExpression = expression.substring(0, operatorInfo.getPos()).trim();
            endExpression = expression.substring(operatorInfo.getPos() + 1).trim();
            numberOne = Double.parseDouble(beginExpression);
            if (next==null){
                return calculate(operatorInfo.operator,numberOne,Double.parseDouble(endExpression));
            }else if(next.operator==Operator.PARENTHESES){
                int rightParentheses = endExpression.indexOf(")");
                if (endExpression.length() == rightParentheses + 1) {
                    return calculate(operatorInfo.operator,numberOne,calculate(endExpression.substring(1, endExpression.length() - 1)));
                } else {
                    String parenthesesExpression = endExpression.substring(1, rightParentheses).trim();
                    String parenthesesRightExpression = endExpression.substring(rightParentheses + 1, endExpression.length());
                    String finalExpression = expression.substring(0,operatorInfo.pos+1) + calculate(parenthesesExpression)+parenthesesRightExpression;
                    return calculate(finalExpression);
                }

            }else {
                if (isFirstCharIsNegative(next)){
                    return calculate(operatorInfo.operator,numberOne,Double.parseDouble(endExpression));
                }else{
                    double numberTwo = Double.parseDouble(endExpression.substring(0,next.pos)) ;
                    String newExpression = calculate(operatorInfo.operator,numberOne,numberTwo)+endExpression.substring(next.pos);
                    return calculate(newExpression);
                }
            }
        }

        //开头是括号
        if (operatorInfo.operator == Operator.PARENTHESES) {
            String beginExpression = null;
            String endExpression = null;
            int rightParentheses = expression.lastIndexOf(")");

            if (expression.length() == rightParentheses + 1) {
                if (isFirstCharIsNegative){
                    return -calculate(expression.substring(operatorInfo.pos+1, expression.length() - 1));
                }
                return calculate(expression.substring(operatorInfo.pos+1, expression.length() - 1));
            } else {
                beginExpression = expression.substring(operatorInfo.pos+1, rightParentheses).trim();
                endExpression = expression.substring(rightParentheses + 1, expression.length());
                String finalExpression = null;
                if (isFirstCharIsNegative){
                     finalExpression = -calculate(beginExpression) + endExpression;
                }else {
                     finalExpression = calculate(beginExpression) + endExpression;
                }
                return calculate(finalExpression);
            }
        }

        return 0;

    }
    private int getParenthesesPos(String expression,OperatorInfo operatorInfo){
        int rightParentheses = expression.indexOf(")");
        int leftParentheses = expression.indexOf("(",operatorInfo.pos+1);
        //查找在第一个左括号和右括号之间还是否有左括号，找到直到没有为止

        while (leftParentheses>0 && leftParentheses<rightParentheses){
            leftParentheses =expression.indexOf("(",leftParentheses+1) ;
        }
        return 0;

    }

    private boolean isFirstCharIsNegative(OperatorInfo operatorInfo) {
        return operatorInfo.operator == Operator.SUB && operatorInfo.pos == 0;
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
        return operatorInfos.stream().sorted((T1,T2)->{
            if (T1.pos>T2.pos){
                return 1;
            }else {
                return -1;
            }
        }).findFirst().get();
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

>>>>>>> 0b225cae4015f04753d1124e04b00f203a083752
}
