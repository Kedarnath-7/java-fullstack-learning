package day05.collectionDemo;

public class Person implements Comparable<Person> {
    private String Fname;
    private String Lname;
    private int age;

    public Person(String Fname, String Lname, int age) {
        this.Fname = Fname;
        this.Lname = Lname;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return Fname + " " + Lname + " " + age;
    }

    @Override
    public int compareTo(Person o) {
        return this.Fname.compareTo(o.getFname());
    }

    @Override
    public boolean equals(Object o) {
        Person p = (Person)o;
        return this.Fname.equals(p.getFname()) && this.Lname.equals(p.getLname());
    }

    @Override
    public int hashCode() {
        return Fname.hashCode() + Lname.hashCode();
    }
}
