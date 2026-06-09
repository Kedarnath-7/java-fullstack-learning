package day03.polymorphism.strings;

import java.util.Scanner;

public class StringOperationsMain {

    public static void main(String[] args) {
        String s1 = "Kedarnath N";
        System.out.println(s1.length());
        System.out.println(s1.charAt(7));
        System.out.println(s1.toUpperCase());
        System.out.println(s1.toLowerCase());
        System.out.println(s1.substring(4));
        System.out.println(s1.substring(2, 3));
        System.out.println(s1.replace("N", "Nagaradone"));
        System.out.println(s1.indexOf('n'));
        System.out.println(s1.lastIndexOf('n'));
        System.out.println(s1.contains("Y"));
        System.out.println(s1.startsWith("K"));
        System.out.println(s1.endsWith("e"));
        System.out.println(s1.concat("Java"));

        String string1 = "Dhoni";
        String string2 = "dhoni";
        System.out.println(string1.equals(string2));
        System.out.println(string1.equalsIgnoreCase(string2));
        System.out.println(string1.compareTo(string2));
        System.out.println(string1.compareToIgnoreCase(string1));
        System.out.println(string2.compareTo(string1));

        // reverse string
        String revS = reverseString(string1);

        System.out.println(revS.equals(string1));
        System.out.println(revS.equalsIgnoreCase(string1));


        // palindrome checker
        System.out.println("Enter a string to check for palindrome: ");
        Scanner sc = new Scanner(System.in);
        String newString = sc.nextLine();
        palindromeChecker(newString);

        // count vowels in a string
        System.out.println("Enter a string to count number of vowels: ");
        String vowelString = sc.nextLine();
        countVowels(vowelString);

        //count spaces in a string
        System.out.println("Enter a string to count for spaces: ");
        String spacesString = sc.nextLine();
        countSpaces(spacesString);

    }

    public static String reverseString(String s){
        String revS = "";
        for(int i = s.length(); i > 0; i--){
            revS += s.charAt(i-1);
        }
        return revS;
    }

    public static void palindromeChecker(String newString){
        int left = 0;
        int right = newString.length()-1;
        while(left < right) {
            if(newString.charAt(left) != newString.charAt(right)) {
                System.out.println("Not Palindrome...");
                return;
            }
            left++;
            right--;
        }
        System.out.println("Yes, palindrome...");
    }

    public static void countVowels(String vowelString){
        int count = 0;
        vowelString = vowelString.toLowerCase();
        String vowelStr = "aeiou";
        for(int i = 0; i < vowelString.length(); i++){
            if(vowelStr.contains(vowelString.substring(i, i+1))){
                count++;
            }
        }
        System.out.println("Vowel count: " + count);
    }

    public static void countSpaces(String spacesString){
        int count = 0;
        for(int i = 0; i < spacesString.length(); i++){
            if(spacesString.charAt(i) == ' '){
                count++;
            }
        }
        System.out.println("Spaces count: " + count);
    }
}

