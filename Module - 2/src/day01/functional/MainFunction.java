package day01.functional;

import java.util.function.*;

public class MainFunction {
    public static void main(String[] args) {
        Function<String, Integer> d = (String s)->s.length();
        System.out.println(d.apply("Hello"));

        // apply()
        BiFunction<Integer, Integer, Integer> mf = (t, u)->t+u;
        System.out.println(mf.apply(1,2));

        // Predicate
        // boolean test(T t)
        Predicate<Integer> predicate = (i)->{
            if(i%2==0)
                return true;
            return false;
        };

        //Unary Operator
        // <T> identity()
        UnaryOperator<String> unaryOperator = (s)->s;


        //Binary Operator
        BinaryOperator<Integer> binaryOperator = (a,b)->a+b;

        //Consumer
        Consumer<String> consumer = (s)->{
            System.out.println(s);
        };

        //Supplier
        Supplier<String> supplier = ()->"Hello";
    }
}
