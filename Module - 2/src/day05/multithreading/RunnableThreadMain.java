package day05.multithreading;

public class RunnableThreadMain {
    public static void main(String[] args) {


//        Thread thread1 = new Thread(new MyRunnable());
//        Thread thread2 = new Thread(new MyRunnable());
//        Thread thread3 = new Thread(new MyRunnable());
//        thread1.start();
//        thread2.start();
//        thread3.start();


        // using lambda
        Runnable task = () -> {
            for (int i = 1; i <= 20; i++) {
                System.out.println(i + " " + Thread.currentThread().getName());
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();


        for(int i = 1; i <= 3; i++){
            new Thread(()->{
                for(int j = 1; j <= 20; j++){
                    System.out.println(j + " " + Thread.currentThread().getName());
                }
            }).start();
        }

        System.out.println("Existing the main thread....");

    }
}
