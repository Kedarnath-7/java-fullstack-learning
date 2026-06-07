package javaAssociation.association.composition;

public class Engine {
    private int horsePower;

    public Engine(int horsePower) {
        this.horsePower = horsePower;
    }
    public int getHorsePower() {
        return horsePower;
    }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
    public String toString() {
        return "Engine: " + horsePower;
    }

}
