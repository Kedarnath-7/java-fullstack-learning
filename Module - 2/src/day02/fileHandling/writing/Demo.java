package day02.fileHandling.writing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo {
    public static void main(String[] args) {
        // this signature is called try with resources
        // it will automatically handle .close()
        // we can pass multiple file to it separated by semicolon

        // Buffer writer is better than writer as it can write chunks of text at a time,
        // instead of just one char at a time

        try(Writer fw = new FileWriter("myFirstFile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);){  // this signature is called resource based try
            fw.write("Hello world\n");
            fw.write("This is file handling in java....\n");
            fw.write("I am writing this text from program....\n");
            System.out.println("File has been written...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
