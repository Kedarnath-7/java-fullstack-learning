package wrapperClasses.intro;

public class MainShort {
    public static void main(String[] args) {
        //Boxing
        short short1 = 20;
        Short sh = Short.valueOf(short1);
        System.out.println(sh);

        //Autoboxing
        Short sh2 = short1;
        System.out.println(sh2);

        //unboxing
        short short2 = sh2.shortValue();
        System.out.println(short2);
    }
}
