package day05.collectionDemo;

public class Animal implements Comparable<Animal> {
    private String owner;
    private int age;
    public Animal(String owner, int age) {
        this.owner = owner;
        this.age = age;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Animal{" + "owner=" + owner + ", age=" + age + '}';
    }

    @Override
    public int compareTo(Animal o) {
        return this.owner.compareTo(o.getOwner());
    }
}
