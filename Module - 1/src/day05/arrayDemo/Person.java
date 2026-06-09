package day05.arrayDemo;

public class Person implements Comparable<Person>{
    private String Fname;
    private String Lname;
    private int age;
    private int choice;

    public Person(String Fname, String Lname, int age) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
//        this.choice = choice;
    }

    public String getPerson(){
        return this.Fname + " " + this.Lname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getFname() {
        return Fname;
    }
    public void setFname(String Fname) {
        this.Fname = Fname;
    }
    public String getLname() {
        return Lname;
    }
    public void setLname(String Lname) {
        this.Lname = Lname;
    }
    public int getChoice() {
        return choice;
    }
    public void setChoice(int choice) {
        this.choice = choice;
    }

//    @Override
//    public int compareTo(Person o) {
//        return this.age-o.age;
//       // return 0.age-this.age;    //descending order
//    }

    @Override
    public int compareTo(Person o){
//        switch(choice){
//            case 1:
//                return this.age-o.age;
//            case 2:
//                return this.Fname.compareTo(o.getFname());
//            case 3:
//                return this.Lname.compareTo(o.getLname());
//            default:
//                System.out.println("Invalid choice....");
//                return 0;
//        }
        System.out.println("Comparing: " + this.getFname() + " and " + o.getFname() + " diff " + this.getFname().compareTo(o.getFname()));
        return this.getFname().compareTo(o.getFname());
    }

    @Override
    public String toString() {
        return Fname + " " + Lname + " " + age;
    }
}
