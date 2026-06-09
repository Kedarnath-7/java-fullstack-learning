package day05.arrayDemo;

import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        System.out.println("Welcome to reversing array program...");
        System.out.println("This program reverses a given integer array...");
        System.out.println("Enter the size of the array: ");
        Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        int[] newArr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            System.out.println("Enter element " + (i + 1) + ": ");
            newArr[i] = sc.nextInt();
        }
        System.out.println("Array elements before swapping....");
        for (int value : newArr) {
            System.out.println(value);
        }
        System.out.println("Array elements after swapping....");
        int left = 0;
        int right = arrSize - 1;
        while (left < right) {
            int temp =  newArr[left];
            newArr[left] = newArr[right];
            newArr[right] = temp;
            left++;
            right--;
        }
        for(int value: newArr){
            System.out.println(value);
        }

    }
}
