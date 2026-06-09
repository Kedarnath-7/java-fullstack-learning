package day01.associationExamples.composition;

public class Engine {
    private int horsePower;

    public Engine(int horsePower) {
        this.horsePower = horsePower;
    }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
    public int getHorsePower() {
        return horsePower;
    }

    public void startEngine() {
        System.out.println("Starting Engine....");
    }
    public void stopEngine() {
        System.out.println("Stopping Engine....");
    }

}
