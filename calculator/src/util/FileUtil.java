package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件类
 */
public class FileUtil {

    public static void write(String fileName, String s){
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            fw.write(s + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear(String fileName){//清空文件内容
        File file = new File(fileName);
        if(!file.exists()) return;
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
