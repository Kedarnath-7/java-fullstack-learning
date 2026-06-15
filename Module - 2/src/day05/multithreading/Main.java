package day05.multithreading;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class Main {
    public static void main(String[] args) {
        try(Writer writer = new FileWriter("output.txt")){
        Thread thread1 = new MyThread("Kedarnath", writer);
        Thread thread2 = new MyThread("Tony", writer);

        // other way to create thread is through runnable
        Thread thread3 = new Thread(new MyRunnable());

        thread1.start();
        thread2.start();

        // Thread joining
        // parent thread waits for the child thread to complete
        // so here the main process thread waits for both of the threads to finish.

        thread1.join();
        //thread2.join();
        writer.write("Active threads count: " + Thread.activeCount() +"\n");
        for(int i = 0; i < 100; i++){
            writer.write(i + " " + Thread.currentThread().getName() + "\n");
        }
        }catch(InterruptedException | IOException e){
            System.out.println("Main thread interrupted....");
        }


    }
}
