package test;

import entity.Fraction;
import org.junit.Test;
import service.Calculate;
import util.Generator;
import util.FileUtil;
import util.Suffix;

public class unitTest {

    private Calculate calculate = new Calculate();

    /**
     * 测试生成题目
     */
    @Test
    public void testGenerator(){
        System.out.println(Generator.generate(10));
    }

    /**
     * 测试写入文件
     */
    @Test
    public void testWriteFile(){
        FileUtil.write("Exercises.txt", Generator.generate(10));
    }

    /**
     * 测试分数四则运算
     */
    @Test
    public void testFractionArithmetic(){
        Fraction f1 = new Fraction(1, 3);
        Fraction f2 = new Fraction(1, 4);
        String operator = "÷";
        String s = calculate.fractionCalculate(f1, f2, operator);
        assert(s.equals("1'1/3"));
    }

    /**
     * 测试将中缀表达式转换为后缀表达式
     */
    @Test
    public void testToSuffixExp(){
        String exp = "9 - 4 × ( 2 ÷ 5 )";
        assert(Suffix.toSuffixExp(exp).equals("9 4 2 5 ÷ × -"));
    }

    /**
     * 测试计算类
     */
    @Test
    public void testCalculate(){
        String exp = "9 - 4 × ( 2 ÷ 5 )";
        String s = Suffix.toSuffixExp(exp);
        assert(calculate.calculate(s).equals("7'2/5"));
    }

}
