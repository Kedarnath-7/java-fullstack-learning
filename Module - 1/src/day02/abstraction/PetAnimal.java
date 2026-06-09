package day02.abstraction;

class PetAnimal{
    private String owner;
    
    public PetAnimal(String owner){
        
        this.owner = owner;
    }
    
    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public void petting() {
        System.out.println("Owner is petting the pet..");
    }

}