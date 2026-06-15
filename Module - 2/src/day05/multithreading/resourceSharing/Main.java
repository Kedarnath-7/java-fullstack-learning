package day05.multithreading.resourceSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            Thread thread1 = new MyThread("Dhoni", new FileInputStream("Dhoni.txt"));
            Thread thread2 = new MyThread("Raina", new FileInputStream("Raina.txt"));

            MyThread.openDestinationWriter();
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            MyThread.closeDestinationWriter();
            System.out.println("Main thread finished....");
        } catch(InterruptedException | IOException e){
            throw  new RuntimeException(e);
        }
    }
}
