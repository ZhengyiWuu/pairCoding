package service;

import util.Suffix;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 检查题目答案
 */
public class CheckAnswer {

    private List<String> wrong = new ArrayList<>();
    private List<String> correct = new ArrayList<>();
    
    public String check(String exerciseFileName, String answerFileName){
        File exerciseFile = new File(exerciseFileName);
        File answerFile = new File(answerFileName);
        if(!exerciseFile.exists() || !answerFile.exists()){
            System.out.println("文件不存在");
            return "ERROR";
        }
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(exerciseFile));
            BufferedReader br2 = new BufferedReader(new FileReader(answerFile));
            String line1, line2;
            while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null){
                String[] str1 = line1.split("\\.");
                String[] str2 = line2.split("\\.");
                Calculate cal = new Calculate();

                String res = cal.calculate(Suffix.toSuffixExp(str1[1]));//计算题目答案
                //System.out.println(res);
                if(res.equals(str2[1])){
                    correct.add(str1[0]);
                }else{
                    wrong.add(str2[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("读取文件失败");
            e.printStackTrace();
        }
        return print();
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        sb.append("Correct: ").append(correct.size()).append(" (");
        printOrder(sb, correct);
        sb.append(")").append("\n");
        sb.append("Wrong: ").append(wrong.size()).append(" (");
        printOrder(sb, wrong);
        sb.append(")").append("\n");
        return sb.toString();
    }

    private void printOrder(StringBuilder sb, List<String> orders){
        for (int i = 0; i < orders.size(); i++){
            if(i == orders.size() - 1){
                sb.append(orders.get(i));
            }else{
                sb.append(orders.get(i)).append(", ");
            }
        }
    }
}
