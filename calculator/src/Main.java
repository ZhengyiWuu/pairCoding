import service.Calculate;
import service.CheckAnswer;
import util.Generator;
import util.FileUtil;
import util.MyException;
import util.Suffix;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toHexString;

public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("输入的参数不足");
            try {
                throw new MyException("输入的参数不足");
            } catch (MyException e) {
                e.printStackTrace();
            }
            return ;
        }
        if (args[0].equals("-n")) {
            // 清空文件的内容
            FileUtil.clear("Exercises.txt");
            FileUtil.clear("Answers.txt");

            Calculate cal = new Calculate();
            HashSet<String> set = new HashSet<>();//对题目去重

            int num = parseInt(args[1]);
            int range = parseInt(args[3]);
            int t = 1;
            while (num > 0) {
                String question = Generator.generate(range);
                String suffixExp = Suffix.toSuffixExp(question);
                String answer = cal.calculate(suffixExp);
                if (set.contains(answer)) {
                    continue;
                }
                set.add(answer);
                if (!answer.equals("ERROR")) {
                    FileUtil.write("Exercises.txt", t + "." + question);
                    FileUtil.write("Answers.txt", t++ + "." + answer);
                    num--;
                }

            }
        } else if (args[0].equals("-e") && args[2].equals("-a")) {
            CheckAnswer checkAnswer = new CheckAnswer();
            String check = checkAnswer.check(args[1], args[3]);
            if (!check.equals("ERROR")) {
                FileUtil.clear("Grade.txt");
                FileUtil.write("Grade.txt", check);
            }
        } else {
            System.out.println("参数输入错误");
            System.out.println("正确输入：-n 10 -r 10 或 -e <exercisefile>.txt -a <answerfile>.txt");
            try {
                throw new MyException("参数输入错误\n正确输入：-n 10 -r 10 或 -e <exercisefile>.txt -a <answerfile>.txt");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }

    }
}
