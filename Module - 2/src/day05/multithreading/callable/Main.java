package day05.multithreading.callable;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> futureVal =executor.submit(new MyCallable());
        try {
            int value = futureVal.get(5, TimeUnit.SECONDS);
            System.out.println("Value: "+value);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }
}

