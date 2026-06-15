package day05.multithreading.deadlock;

public class DeadLockMain {

    // Deadlock scenario

//    public static void main(String[] args) {
//        Object chopStick1 = new Object();
//        Object chopStick2 = new Object();
//        Thread philosopher1 = new Thread(()->{
//            System.out.println("Philosopher 1 is occupying chopstick 1");
//            synchronized (chopStick1) {
//                System.out.println("Philosopher 1 occupied chopstick 1, going for chopstick 2");
//                try{
//                    Thread.sleep(500);
//
//                }catch(InterruptedException e){
//                    throw new RuntimeException(e);
//                }
//                synchronized (chopStick2) {
//                    System.out.println("Philospher 1 is eating...");
//                }
//            }
//        });
//        Thread philosopher2 = new Thread(()->{
//            System.out.println("Philosopher 2 is occupying chopstick 1");
//            synchronized (chopStick2) {
//                System.out.println("Philosopher 2 occupied chopstick 2, going for chopstick 1");
//                try{
//                    Thread.sleep(500);
//
//                }catch(InterruptedException e){
//                    throw new RuntimeException(e);
//                }
//                synchronized (chopStick1) {
//                    System.out.println("Philosopher 2 is eating...");
//                }
//            }
//        });
//
//        philosopher1.start();
//        philosopher2.start();


        // solving deadlock or race on condition

        public static void main(String[] args) {
            class Philosopher implements Runnable{
                private static Object chopstick1 = new Object();
                private static Object chopstick2 = new Object();
                private String name;

                public Philosopher(String name) {
                    this.name = name;
                }

                @Override
                public void run() {
                    System.out.println(this.name+ " is occupying chopstick 1");
                    synchronized (chopstick1){
                        System.out.println(this.name+" occupied chopstick 1 ,going for chopstick 2");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        synchronized (chopstick2){
                            System.out.println(this.name+" occupied chopstick2 and is now eating...");
                        }
                    }

                }
            }


            Thread philosopher1 = new Thread(new Philosopher("Philosopher 1"));
            Thread philosopher2 = new Thread(new Philosopher("Philosopher 2"));
            philosopher1.start();
            philosopher2.start();
        }
    }

