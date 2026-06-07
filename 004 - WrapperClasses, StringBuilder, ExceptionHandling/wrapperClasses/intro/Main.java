package wrapperClasses.intro;

public class Main {
    public static void main(String[] args) {
        int value = 10;
        Integer i = Integer.valueOf(value);
        System.out.println(i); // Boxing

        // AutoBoxing
        Integer j = value;
        System.out.println(j);

        //unboxing
        int unboxedInt = i.intValue();
        System.out.println(unboxedInt);

    }
}
