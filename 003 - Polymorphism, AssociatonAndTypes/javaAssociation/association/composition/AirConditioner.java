package javaAssociation.association.composition;

public class AirConditioner {
    private double tons;
    public AirConditioner(double tons) {
        this.tons = tons;
    }
    public double getTons() {
        return tons;
    }
    public void setTons(double tons) {
        this.tons = tons;
    }
    public String toString(){
        return "Air Conditioner: " + tons;
    }
}
