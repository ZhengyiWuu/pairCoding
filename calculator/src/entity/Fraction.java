package entity;
/*分数类*/
public class Fraction {

    private int numerator;//分子
    private int denominator;//分母

    public Fraction(int numerator, int denominator){// 将分数化为最简

        int gcd = __gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
    /*辗转相除法*/
    private int __gcd(int x, int y){
        if(y == 0) return x;
        return __gcd(y, x % y);
    }
    /*重载，表示为真分数的形式*/
    @Override
    public String toString() {
        if(numerator < denominator) return numerator + "/" + denominator;
        else if (numerator % denominator == 0) return String.valueOf(numerator / denominator);
        return (numerator / denominator) + "'" + (numerator % denominator) + "/" + denominator;
    }


}
