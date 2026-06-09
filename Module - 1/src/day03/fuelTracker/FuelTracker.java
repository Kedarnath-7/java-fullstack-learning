package day03.fuelTracker;

public class FuelTracker {
    private double fuelCapacity;
    private int mileage;
    private double currentFuelAmount;

    public FuelTracker(double fuelCapacity, int mileage, double currentFuelAmount) {
        this.fuelCapacity = fuelCapacity;
        this.mileage = mileage;
        this.currentFuelAmount = currentFuelAmount;
    }

    public void fillFuel(double amount){
        if(currentFuelAmount + amount <= fuelCapacity){
            currentFuelAmount += amount;
        }else{
            System.out.println("The fuel amount is too large...try again with smaller amount. Choose check fuel to know current amount..");
        }
    }

    public void checkFuel(){
        System.out.println("Current Fuel Amount: " + currentFuelAmount);
    }

    public void drive(double length){
        double possibleDistance = mileage * currentFuelAmount;
        double fuelUsed = length / mileage;
        if(possibleDistance >= length){
            System.out.println("The fuel is sufficient for given distance...starting the journey...");
            currentFuelAmount -= fuelUsed;
        }else{
            System.out.println("The current fuel amount is insufficient for this drive...fill with " + fuelUsed + " to proceed with drive.");
        }

    }
}
