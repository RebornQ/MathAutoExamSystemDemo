package utils;

import java.io.*;
import java.util.ArrayList;

public class IOUtil {

    public static String [] readAllLines(String fileName){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] array = new String[arrayList.size()];
        return arrayList.toArray(array);
    }

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

    public static void clearFile(String fileName){
        File file = new File(fileName);
        if(file.exists()) {//文件存在返回true
            file.delete();
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

    public static void createDir(String fileName) {
    	File file = new File(fileName);
         if (!file.exists()) {
             file.mkdir();
         }
     }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}
