package util;

import entity.Fraction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generator {
    private static final String[] OPERATOR = {"+", "-", "×", "÷"};
    private static final Random R = new Random();

    public static String generate(int maximum) {

        int cnt = R.nextInt(3) + 2;

        List<String> exp = new LinkedList<>();
        for (int i = 0; i < cnt; i++) {
            exp.add(String.valueOf(getFraction(maximum)));
            if (i != cnt - 1) {
                exp.add(OPERATOR[R.nextInt(4)]);
            }
        }
        if (R.nextInt(9) == 0) {
            List<Integer> index = new ArrayList<>();
            for (int i = 0; i < exp.size(); i += 2) {
                index.add(i);
            }
            int left = index.get(R.nextInt(index.size() - 1));
            int j = 0;
            for (int i = 0; i < index.size(); i++) {
                if (left < index.get(i)) {
                    index.set(j++, index.get(i) + 1);
                }
            }
            int right = index.get(R.nextInt(j)) + 1;
            exp.add(left, "(");
            exp.add(right, ")");//加括号
        }
        StringBuilder sb = new StringBuilder();
        for (String s : exp) {
            sb.append(s).append(" ");
        }
        return sb.toString();
    }

    public static Fraction getFraction(int maximum) {
        //调整随机数为整数或者分数
        boolean isFraction = R.nextBoolean();
        return isFraction ? new Fraction(R.nextInt(maximum) + 1, R.nextInt(maximum) + 1) : new Fraction(R.nextInt(maximum) + 1, 1);
    }
}
