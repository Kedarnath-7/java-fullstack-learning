package day05.arrayDemo;

import java.util.Arrays;

public class ArraySorting {
    public static void main(String[] args) {
        Integer[] intArr = {45, 39, 90, 23, 34, 56};
        System.out.println("Array before sorting....");
        System.out.println(Arrays.toString(intArr));
        Arrays.sort(intArr);
        System.out.println("Array after sorting....");
        System.out.println(Arrays.toString(intArr));


        String[] stringArr = {"Sachin", "Dhoni", "Kohli", "Rohit", "Raina"};
        System.out.println("Array before sorting....");
        System.out.println(Arrays.toString(stringArr));
        Arrays.sort(stringArr);
        System.out.println("Array after sorting....");
        System.out.println(Arrays.toString(stringArr));
    }
}
