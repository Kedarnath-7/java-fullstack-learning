package day02.fileHandling.outputStream;

import java.io.*;

public class Demo {
    public static void main(String[] args) {
        try(OutputStream fos = new FileOutputStream("outputStream.txt");) {
            String data = "Hello world";
            fos.write(data.getBytes());
            System.out.println("Data written to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // buffered output stream
        try(OutputStream fos = new FileOutputStream("outputStream.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);) {
            String data = "Hello buffered output stream";
            bufferedOutputStream.write(data.getBytes());
            System.out.println("Data written to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
