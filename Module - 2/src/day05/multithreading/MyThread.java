package day05.multithreading;

import java.io.IOException;
import java.io.Writer;

public class MyThread extends Thread{
    private Writer writer;
    public MyThread(String task, Writer write){
        super(task);
        this.writer = write;
    }

    @Override
    public void run(){
        try{
            if(this.getName().equals("Tony")){
                for(int i = 0; i < 1000; i++){
                    this.writer.write(i + " " + this.getName() + "\n");

                }
            }else{
                for(int i = 0; i < 100; i++){
                    this.writer.write(i + " " + this.getName() + "\n");

                }
            }
        }catch( IOException e){
            System.out.println(e.getMessage());
        }
    }

}
