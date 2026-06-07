package javaAssociation.association.composition;

public class Car {
    private Engine engine;
    private MusicSystem musicSystem;
    private AirConditioner airConditioner;

    public Car(Engine engine, MusicSystem musicSystem, AirConditioner airConditioner){
        this.engine = engine;
        this.musicSystem = musicSystem;
        this.airConditioner = airConditioner;
    }
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public MusicSystem getMusicSystem() {
        return musicSystem;
    }
    public void setMusicSystem(MusicSystem musicSystem) {
        this.musicSystem = musicSystem;
    }
    public AirConditioner getAirConditioner() {
        return airConditioner;
    }
    public void setAirConditioner(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String toString(){
        return engine + " " + airConditioner + " " + musicSystem;
    }
}

