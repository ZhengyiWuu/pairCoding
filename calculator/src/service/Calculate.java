package service;

import entity.Fraction;
import util.Suffix;

import java.util.*;
/*
* 计算类
* */
public class Calculate {
    public String calculate(String exp){
        Stack<String> st = new Stack<>();
        for(String s: exp.split(" ")){
            if(Suffix.opValue.containsKey(s)){//是运算符取出栈顶的两个元素进行运算并将结果压入栈
                String result;
                String fir = st.pop();
                String sec = st.pop();
                if(isFraction(fir) || isFraction(sec)){
                    result = fractionCalculate(strToFraction(sec), strToFraction(fir), s);
                }else {
                    result = Calculate(Integer.parseInt(sec), Integer.parseInt(fir), s);
                }
                // 表达式错误
                if(result.equals("-1")) {
                    st.clear();
                    return "ERROR";
                }
                st.push(result);//结果入栈顶
            }else{
                st.push(s);//操作数入栈
            }
        }
        return st.pop();
    }
 
    private boolean isFraction(String s){
        return s.contains("/");
    }

    public Fraction strToFraction(String s){
        int numerator, denominator;
        if (s.contains("'")){
            denominator = Integer.parseInt(s.split("/")[1]);
            String[] split = s.split("/")[0].split("'");
            numerator = Integer.parseInt(split[0]) * denominator + Integer.parseInt(split[1]);
        }else if(s.contains("/")){
            //真分数
            numerator = Integer.parseInt(s.split("/")[0]);
            denominator = Integer.parseInt(s.split("/")[1]);
        }else{
            //整数
            numerator = Integer.parseInt(s);
            denominator = 1;
        }
        return new Fraction(numerator, denominator);
    }
    /*整数运算*/
    public String Calculate(int num1, int num2, String op){
        int result = -1;
        switch (op){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                if(num1 >= num2){
                    result = num1 - num2;
                }
                break;
            case "×":
                result = num1 * num2;
                break;
            case "÷":
            default:
                // 分母不能为0
                if(num2 == 0) return String.valueOf(-1);
                if(num1 % num2 == 0){
                    result = num1 / num2;
                }else {
                    return new Fraction(num1, num2).toString();
                }

        }
        return String.valueOf(result);
    }
    
    /*分数运算*/
    public String fractionCalculate(Fraction fraction1, Fraction fraction2, String op){
        // 获取分数的分子、分母
        int Numerator1 = fraction1.getNumerator();
        int Denominator1 = fraction1.getDenominator();
        int Numerator2 = fraction2.getNumerator();
        int Denominator2 = fraction2.getDenominator();
        // 新分数的分子、分母
        int Numerator3, Denominator3;
        switch (op){
            case "+":
                Numerator3 = Numerator1  * Denominator2 + Numerator2 * Denominator1;
                Denominator3 = Denominator1 * Denominator2;
                break;
            case "-":
                //计算过程不能产生负数
                if((Numerator3 = Numerator1  * Denominator2 - Numerator2 * Denominator1) < 0){
                    return String.valueOf(-1);
                }else{
                    Denominator3 = Denominator1 * Denominator2;
                }
                break;
            case "×":
                Numerator3 = Numerator1  * Numerator2;
                Denominator3 = Denominator1 * Denominator2;
                break;
            case "÷":
            default:
                Numerator3 = Numerator1  * Denominator2;
                Denominator3 = Denominator1 * Numerator2;
                //分母不能为0
                if(Denominator3 == 0) return String.valueOf(-1);
        }


        if(Numerator3 % Denominator3 == 0){//运算结果为整数
            return String.valueOf(Numerator3 / Denominator3);
        }
        return new Fraction(Numerator3, Denominator3).toString();
    }

}
