package day02.abstraction;

class Dog extends PetAnimal implements Animal{

    public Dog(String owner){
        super(owner);
    }
    public void eat() {
        System.out.println("Dog is eating pedigree...");
    }

    public void talk() {
        System.out.println("Dog is barking at the stranger..");
    }

    public void walk() {
        System.out.println("Dog is walking on the footpath..");
    }
    public void shelter() {
        System.out.println("Dog is taking shelter under a tree..");
    }
}