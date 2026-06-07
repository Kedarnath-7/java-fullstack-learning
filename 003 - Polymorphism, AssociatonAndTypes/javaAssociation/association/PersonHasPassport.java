package javaAssociation.association;

public class PersonHasPassport {
    private String Fname;
    private String Lname;
    private int age;
    private Passport passport;

    public PersonHasPassport(String Fname, String Lname, int age){
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
    }
    public PersonHasPassport(String Fname, String Lname, int age, Passport passport){
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
        this.passport = passport;
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
    public Passport getPassport() {
        return passport;
    }
    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String toString() {
        return " " + Fname + " " + Lname + " " + age + " " + passport.toString();
    }
}
