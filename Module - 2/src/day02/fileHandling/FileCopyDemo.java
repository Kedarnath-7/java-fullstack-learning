package day02.fileHandling;


import java.io.*;

public class FileCopyDemo {
    public static void main(String[] args) {

        try (
                BufferedInputStream bis =
                        new BufferedInputStream(
                                new FileInputStream("sample.pdf"));

                BufferedOutputStream bos =
                        new BufferedOutputStream(
                                new FileOutputStream("output.pdf"))
        ) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied successfully!");

        } catch (IOException e) {
            System.out.println("Error while reading from file");
        }
    }
}
