package day02.fileHandling;


import java.io.*;

public class ImageCopyDemo {
    public static void main(String[] args) {

        try (
                BufferedInputStream bis =
                        new BufferedInputStream(
                                new FileInputStream("cat.jpg"));

                BufferedOutputStream bos =
                        new BufferedOutputStream(
                                new FileOutputStream("cat_copy.jpg"))
        ) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            System.out.println("Image copied successfully!");

        } catch (IOException e) {
            System.out.println("Error while reading from file");;
        }
    }
}