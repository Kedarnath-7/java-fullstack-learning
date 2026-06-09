package day05.arrayDemo;

public class Child {
    private String Fname;
    private String Lname;
    private String DOB;
    private int day;
    private int month;
    private int year;

    public Child(String Fname, String Lname, String DOB) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.DOB = DOB;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public String getFname() {
        return this.Fname;
    }
    public void setFname(String Fname) {
        this.Fname = Fname;
    }
    public String getLname() {
        return this.Lname;
    }
    public void setLname(String Lname) {
        this.Lname = Lname;
    }
    public String getDOB() {
        return this.DOB;
    }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return Fname + " " + Lname + " " + DOB;
    }
}
