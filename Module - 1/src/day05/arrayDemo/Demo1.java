package day05.arrayDemo;

public class Demo1 {
    public static void main(String[] args) {

        // An array is collection elements of similar data type
        // stored in contiguous memory locations

        //int array
        int arr[] = new int[5];
        //int arr[] = {1, 2, 3, 4, 5};
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        System.out.println("Int array...");
        System.out.println("Length: " + arr.length);
        for(int i=0;i<arr.length;i++){
            System.out.println("i: " + arr[i]);
        }

        // enhanced for loop
        for(int value: arr){
            System.out.println("value: " + value);
        }

        //short array
        short arrShort[] = new short[3];
        arrShort[0] = 1;
        arrShort[1] = 2;
        arrShort[2] = 3;
        System.out.println("Length: " + arrShort.length);
        System.out.println("Short Array....");
        for(int i=0;i<arrShort.length;i++){
            System.out.println("i: " + arrShort[i]);
        }


        // String array
        String arrString[] = new String[10];
        arrString[0] = "Tony";
        arrString[1] = "Peter";
        arrString[2] = "Steve";
        arrString[3] = "Kedar";
        System.out.println("String Array..");
        System.out.println("Length: " + arrString.length);
        for(int i=0;i<arrString.length;i++){
            System.out.println("i: " + arrString[i]);
        }


        // char array
        char arrChar[] = new char[5];
        arrChar[0] = 'K';
        arrChar[1] = 'E';
        arrChar[2] = 'D';
        arrChar[3] = 'A';
        arrChar[4] = 'R';
        System.out.println("Char Array..");
        System.out.println("Length: " + arrChar.length);
        for(int i=0;i<arrChar.length;i++){
            System.out.println("i: " + arrChar[i]);
        }

        // float array
        float arrFloat[] = new float[5];
        arrFloat[0] = 10f;
        arrFloat[1] = 5f;
        arrFloat[2] = 15f;
        System.out.println("Float Array...");
        System.out.println("Length: " + arrFloat.length);
        for(int i=0;i<arrFloat.length;i++){
            System.out.println("i: " + arrFloat[i]);
        }

        //double array
        double arrDouble[] = new double[5];
        arrDouble[0] = 3d;
        arrDouble[1] = 5d;
        arrDouble[2] = 2d;
        System.out.println("Double Array...");
        System.out.println("Length: " + arrDouble.length);
        for(int i=0;i<arrDouble.length;i++){
            System.out.println("i: " + arrDouble[i]);
        }

        // byte array
        byte arrByte[] = {0, 3, 4, 5};
        System.out.println("Byte Array...");
        System.out.println("Length: " + arrByte.length);
        for(byte value:  arrByte){
            System.out.println("value: " + value);
        }


        // wrapper class Integer
        Integer arrInteger[] = {10, 20, 30, 40};
        System.out.println("Integer Array...");
        System.out.println("Length: " + arrInteger.length);
        for(Integer num:arrInteger){
            System.out.println("num: " + num);
        }
    }
}
