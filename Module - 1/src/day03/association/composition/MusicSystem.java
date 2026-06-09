package day03.association.composition;

public class MusicSystem {
    private String brand;
    public MusicSystem(String brand){
        this.brand = brand;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public String toString(){
        return "Brand: " + brand;
    }
}
