package day05.multithreading;

public class DaemonMain {
    public static void main(String[] args) {
        CustomThread thread1 = new CustomThread("Kedarnath", 500);
        CustomThread thread2 = new CustomThread("Tony", 1000);
        CustomThread thread3 = new CustomThread("Peter", 1500);



        // daemon threads
        // daemon process should be set before calling start()
        thread2.setDaemon(true);
        thread3.setDaemon(true);

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("Exiting main.....");

    }
}
