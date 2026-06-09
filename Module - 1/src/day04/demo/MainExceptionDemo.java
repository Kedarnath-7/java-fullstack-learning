package day04.demo;

import java.io.FileNotFoundException;

public class MainExceptionDemo {
    public static void main(String[] args) {
        try{
            demo();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    //checked exception should be handled by either try & catch or use throws keyword
    // in method signature, in the main do not use throws, always use try & catch
    public static void demo () throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    // custom exceptions can be made checked or unchecked by using Exception for checked & RunTimeException for unchecked
}
