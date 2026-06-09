package day01;

class Second{
    public static void main(String[] args){

        String n1 = "10";
        String n2 = "20";
        int sum = Integer.parseInt(n1) + Integer.parseInt(n2);
        System.out.println("The sum of n1 and n2 is: " + sum);


        byte a = 127;
        byte b = 20;
        int aInt = (int)a;
        int bInt = (int)b;
        int sumInt = aInt + bInt;

        System.out.println("The sum of a and b is: " + sumInt);

        String boolStr = "true";
        boolean boolVal = Boolean.parseBoolean(boolStr);
        System.out.println("The boolean value is: " + boolVal);

        String boolStr2 = String.valueOf(boolVal);
        System.out.println("The boolean value as a string is: " + boolStr2);


        // all other possible conversions
        // int to float
        int intVal = 100;
        float floatVal = (float)intVal;
        System.out.println("The float value is: " + floatVal);

        // int to double
        double doubleVal = (double)intVal;
        System.out.println("The double value is: " + doubleVal);

        // float to int
        float floatVal2 = 100.5f;
        int intVal2 = (int)floatVal2;
        System.out.println("The int value is: " + intVal2);

        // double to int
        double doubleVal2 = 100.5;
        int intVal3 = (int)doubleVal2;
        System.out.println("The int value is: " + intVal3);

        // char to int
        char charVal = 'A';
        int charIntVal = (int)charVal;
        System.out.println("The int value of char 'A' is: " + charIntVal);

        // int to char
        int intVal4 = 66;
        char charVal2 = (char)intVal4;
        System.out.println("The char value of int 66 is: " + charVal2);

        // String to char
        String strVal = "Hello";
        char charVal3 = strVal.charAt(0);
        System.out.println("The first character of the string 'Hello' is: " + charVal3);

        // char to String
        char charVal4 = 'H';
        String strVal2 = String.valueOf(charVal4);
        System.out.println("The string value of char 'H' is: " + strVal2);

        //
    }
}