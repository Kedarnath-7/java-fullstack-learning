package wrapperClasses.intro;

public class MainFloat {
    public static void main(String[] args) {
        //boxing
        float i = 20.50f;
        Float f = Float.valueOf(i);
        System.out.println(f);

        //Autoboxing
        Float f2 = i;
        System.out.println(f2);

        //unboxing
        float j = f.floatValue();
        System.out.println(j);
    }
}
