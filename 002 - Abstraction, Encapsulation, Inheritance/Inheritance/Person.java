package Inheritance;

class Person{

    protected String Fname;
    protected String Lname;
    protected int age;

    public Person(String Fname, String Lname, int age){
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
    }
    
    public void setFname(String Fname){
        this.Fname = Fname;
    }
    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getFname(){
        return this.Fname;
    }

    public String getLname(){
        return this.Lname;
    }

    public int getAge(){
        return this.age;
    }

    public void eat(){
        System.out.println(this.Fname + " " + this.Lname + " is eating.");
    }

    public void walk(){
        System.out.println(this.Fname + " " + this.Lname + " is walking.");
    }

    public void talk(){
        System.out.println(this.Fname + " " + this.Lname + " is talking.");
    }

    public void getDetails(){
        System.out.println("The person is " + this.Fname + " " + this.Lname + " of " + this.age + " years old.");
    }
}

