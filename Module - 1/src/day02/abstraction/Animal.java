package day02.abstraction;

// abstract class Animal {          ====> this is 100% abstraction, this cannot solve for code reusable, so we use interface
//     public abstract void eat();
//     public abstract void talk();
//     public abstract void walk();
//     public abstract void shelter();
// }  


interface Animal {
    public void eat();
    public void talk();
    public void walk();
    public void shelter();
}