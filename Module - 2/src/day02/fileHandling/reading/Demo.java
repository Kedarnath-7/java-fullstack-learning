package day02.fileHandling.reading;

import java.io.*;

public class Demo {
    public static void main(String[] args) {

        // Reader reads one character at a time, and it is time-consuming
        // buffer reader can one line at a time with .readLine()

        try(Reader reader = new FileReader("myFirstFile.txt");
            BufferedReader bufferReader = new BufferedReader(reader);){
            // int value; for reader
            String line;
            do{
                // value = reader.read();
                line = bufferReader.readLine();
                if(line != null){
                    System.out.println(line);
                }
            }while(line != null);
        }catch (FileNotFoundException e){
            System.out.println("File not found....");
        }catch (IOException e){
            System.out.println("Error reading file....");
        }
    }
}
