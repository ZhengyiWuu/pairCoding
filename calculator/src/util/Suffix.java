package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 转为逆波兰表达式
 */
public class Suffix {
    /**
     * 将中缀表达式转换为后缀表达式
     */
    public static Map<String, Integer> opValue = new HashMap<>();

    static {
        opValue.put("×", 1);
        opValue.put("÷", 1);
        opValue.put("+", 0);
        opValue.put("-", 0);
    }
    public static String toSuffixExp(String exp) {
        StringBuilder nums = new StringBuilder();
        Stack<String> ops = new Stack();

        for (String s : exp.split(" ")) {
            if (opValue.containsKey(s)) {
                while (!ops.isEmpty() && !ops.peek().equals("(") && (opValue.get(ops.peek()) >= opValue.get(s))) {
                    nums.append(ops.pop()).append(" ");
                }
                ops.push(s);
            } else if (s.equals("(")) {
                ops.push(s);
            } else if (s.equals(")")) {
                while (!ops.isEmpty()) {
                    if (ops.peek().equals("(")) {
                        ops.pop();
                        break;
                    } else {
                        nums.append(ops.pop()).append(" ");
                    }
                }
            } else {
                nums.append(s).append(" ");
            }
        }
        while (ops.size() > 0) {
            nums.append(ops.pop()).append(" ");
        }
        return nums.toString();
    }
}
