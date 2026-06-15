package day05.multithreading.resourceSharing;

import java.io.*;

public class MyThread extends Thread{
    private static OutputStream destinationWriter;
    private InputStream sourceReader;

    public MyThread(String name, InputStream sourceReader) {
        super(name);
        this.sourceReader = sourceReader;
    }

    public static void openDestinationWriter() throws FileNotFoundException {
        MyThread.destinationWriter = new FileOutputStream("Output.log");
    }
    public static void closeDestinationWriter() throws IOException {
        destinationWriter.close();
    }

    @Override
    public void run() {
        //
        // synchronized - also called thread safe
        // locks the object so that only any one thread can use that object
        // it is like locking an object and giving the key to only one thread
        // and that object is unlocked and can be used by other thread only after the thread
        // holding the key is finished
        //

        synchronized (destinationWriter) {
            System.out.println("Thread name: " + Thread.currentThread().getName());
            int c;
            try {
                while ((c = sourceReader.read()) != -1) {
                    destinationWriter.write(c);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try{
                    sourceReader.close();
                }catch (IOException e){
                    System.out.println("Error closing reader");
                }
            }
        }
    }

}
