package day05.multithreading;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 1; i <= 20; i++){
            System.out.println(i + " " + Thread.currentThread().getName());
        }
    }
}
