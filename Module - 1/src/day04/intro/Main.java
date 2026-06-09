package day04.intro;

public class Main {
    public static void main(String[] args) {

        // String Builder
        StringBuilder sb = new StringBuilder("Kedar");
        System.out.println("Before modifying: \n" + sb.hashCode() + " - " + sb);
        sb.append("nath");
        System.out.println("After modifying: \n" + sb.hashCode() + " - " + sb);
        StringBuilder sb3 = new StringBuilder("");
        System.out.println(sb3.capacity());

        // String Buffer
        StringBuffer sb2 = new StringBuffer("N");
        System.out.println("Before modifying: \n" + sb2.hashCode() + " - " + sb2);
        sb2.append("Kedarnath");
        System.out.println("After modifying: \n" + sb2.hashCode() + " - " + sb2);

        // methods

        //insert
        sb.insert(0, "Hi");
        System.out.println(sb);

        //delete
        sb2.delete(0, 1);
        System.out.println(sb2);

        //replace
        sb.replace(5, 8, "N");
        System.out.println(sb);

        //reverse
        sb2.reverse();
        System.out.println(sb2);
    }
}
