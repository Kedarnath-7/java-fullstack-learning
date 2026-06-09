package day04.wrapperClasses.intro;

public class MainDouble {
    public static void main(String[] args) {
        // boxing
        double d = 40.23d;
        Double double1 = Double.valueOf(d);
        System.out.println(double1);

        //Autoboxing
        Double double2 = d;
        System.out.println(double2);

        //unboxing
        double d2 = double2.doubleValue();
        System.out.println(d2);
    }
}
