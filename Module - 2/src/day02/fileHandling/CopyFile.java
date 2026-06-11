package day02.fileHandling;

import java.io.*;
import java.nio.Buffer;

public class CopyFile {
    public static void main(String[] args) {

//        try(Reader fr = new FileReader("FileHandling.txt");
//            Writer fw = new FileWriter("CopiedFile.txt");) {
//            int value;
//            do{
//                value = fr.read();
//                if(value != -1){
//                    fw.write(value);
//                }
//            }while(value!=-1);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // using buffered reader and writer
        try(Reader fr = new FileReader("FileHandling.txt"); BufferedReader bufferedReader = new BufferedReader(fr);
            Writer fw = new FileWriter("CopiedFile.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fw);) {

            String line;
            do{
                line = bufferedReader.readLine();
                if(line != null){
                    bufferedWriter.write(line);
                }

            }while(line != null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
