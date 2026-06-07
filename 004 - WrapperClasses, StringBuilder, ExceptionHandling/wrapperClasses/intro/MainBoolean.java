package wrapperClasses.intro;

public class MainBoolean {
    public static void main(String[] args) {
        // boxing
        boolean bool = true;
        Boolean b = Boolean.valueOf(bool);
        System.out.println(b);

        //Autoboxing
        Boolean b1 = bool;
        System.out.println(b1);

        //unboxing
        boolean unboxedBool = b.booleanValue();
        System.out.println(unboxedBool);
    }
}
