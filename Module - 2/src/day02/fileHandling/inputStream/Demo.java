package day02.fileHandling.inputStream;

import java.io.*;

public class Demo {
    public static void main(String[] args) {
        try(InputStream fis = new FileInputStream("outputStream.txt");){
            int value;
            while((value = fis.read()) != -1){
                System.out.print((char)value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // buffered input stream
        try(InputStream fis = new FileInputStream("outputStream.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);){
            byte[] buffer = new byte[1024];
            int bytesRead;

            while((bytesRead = bufferedInputStream.read(buffer)) != -1){
                System.out.print((char)bytesRead);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
