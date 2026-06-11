package day02.streams.entity;

public class Student {
    private String name;
    private double phys;
    private double chem;
    private double math;
    private double hist;
    private double geog;

    public Student(String name, double phys, double chem, double math, double hist, double geog) {
        this.name = name;
        this.phys = phys;
        this.chem = chem;
        this.math = math;
        this.hist = hist;
        this.geog = geog;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPhys() {
        return phys;
    }
    public void setPhys(double phys) {
        this.phys = phys;
    }
    public double getChem() {
        return chem;
    }
    public void setChem(double chem) {
        this.chem = chem;
    }
    public double getMath() {
        return math;
    }
    public void setMath(double math) {
        this.math = math;
    }
    public double getHist() {
        return hist;
    }
    public void setHist(double hist) {
        this.hist = hist;
    }
    public double getGeog() {
        return geog;
    }
    public void setGeog(double geog) {
        this.geog = geog;
    }
    @Override
    public String toString() {
        return "{Name: " + this.name + ", Phys: " + this.phys + ", Chem: " + this.chem +  ", Math: " + this.math + ", Hist: " + this.hist + ", Geog: " + this.geog + "}";
    }
}
