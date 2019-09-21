package utils;

import java.io.*;
import java.util.ArrayList;

public class IOUtil {

    public static void writeLine(String fileName,String str){
        PrintWriter printWriter = null;
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file,true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(printWriter != null){
                printWriter.close();
            }
            if(fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createFile(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
