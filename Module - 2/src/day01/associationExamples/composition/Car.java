package day01.associationExamples.composition;

public class Car {
    private String model;
    private Engine engine;
    public Car(String model, int horsePower) {
        this.model = model;
        this.engine = new Engine(horsePower);
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void startCar() {
        engine.startEngine();
        System.out.println("Starting Car..." + this.getModel());
    }
    public void stopCar() {
        engine.stopEngine();
        System.out.println("Stopping Car..." + this.getModel());
    }

}
