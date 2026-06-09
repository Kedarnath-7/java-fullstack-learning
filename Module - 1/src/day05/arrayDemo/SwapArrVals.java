package day05.arrayDemo;
import java.util.Scanner;
public class SwapArrVals {
    public static void main(String[] args) {
        System.out.println("Welcome to swapping of array program...");
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        System.out.println("Enter the size of the array: ");
        int arrSize = sc.nextInt();
        sc.nextLine();
        String stringArr[] = new String[arrSize];
        System.out.println("Enter the " + arrSize + " elements of the array: ");
        for (int i = 0; i < arrSize; i++) {
            stringArr[i] = sc.nextLine();
        }
        System.out.println("Array before swapping: ");
        for (String value : stringArr) {
            System.out.println(value);
        }
        System.out.println("Enter first index to swap: ");
        int indexFirst = sc.nextInt();
        System.out.println("Enter second index to swap: ");
        int indexSecond = sc.nextInt();
        String temp = stringArr[indexFirst];
        stringArr[indexFirst] = stringArr[indexSecond];
        stringArr[indexSecond] = temp;
        System.out.println("Array after swapping: ");
        for (String value : stringArr) {
            System.out.println(value);
        }

//        while(flag){
//            System.out.println("Please choose the type of array you want: 1. int\n2. float\n3. String\n4. short");
//            int dataChoice = sc.nextInt();
//            System.out.println("Please enter the size of the array you want: ");
//            int arrSize = sc.nextInt();
//            switch (dataChoice) {
//                case 1:
//                    int intArr[] = new int[arrSize];
//                    for (int i = 0; i < arrSize; i++) {
//                        intArr[i] = sc.nextInt();
//                    }
//                    System.out.println("Array before swapping: ");
//                    for (int value: intArr) {
//                        System.out.println(value);
//                    }
//                    System.out.println("Enter first index to swap: ");
//                    int firstIndex = sc.nextInt();
//                    System.out.println("Enter second index to swap: ");
//                    int secondIndex = sc.nextInt();
//                    int temp = intArr[firstIndex];
//                    intArr[firstIndex] = intArr[secondIndex];
//                    intArr[secondIndex] = temp;
//                    System.out.println("Array after swapping: ");
//                    for (int value: intArr) {
//                        System.out.println(value);
//                    }
//                    flag = false;
//                    break;
//                case 2:
//                    float floatArr[] = new float[arrSize];
//                    for (int i = 0; i < arrSize; i++) {
//                        floatArr[i] = sc.nextFloat();
//                    }
//                    System.out.println("Array before swapping: ");
//                    for (float value: floatArr) {
//                        System.out.println(value);
//                    }
//                    System.out.println("Enter first index to swap: ");
//                    int firstIndex = sc.nextInt();
//                    System.out.println("Enter second index to swap: ");
//                    int secondIndex = sc.nextInt();
//                    float temp = floatArr[firstIndex];
//                    flotArr[firstIndex] = floatArr[secondIndex];
//                    floatArr[secondIndex] = temp;
//                    System.out.println("Array after swapping: ");
//                    for (float value: floatArr) {
//                        System.out.println(value);
//                    }
//
//                    flag = false;
//                    break;
//                case 3:
//
//
//                    flag = false;
//                    break;
//                case 4:
//                    short shortArr[] = new short[arrSize];
//                    for (int i = 0; i < arrSize; i++) {
//                        shortArr[i] = sc.nextShort();
//                    }
//                    flag = false;
//                    break;
//                default:
//                    System.out.println("Invalid choice...try again");
//                    break;
//            }
//    }
    }
}
