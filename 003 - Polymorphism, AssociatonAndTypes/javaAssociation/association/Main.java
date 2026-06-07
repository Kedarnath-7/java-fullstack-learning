package javaAssociation.association;

public class Main {
    public static void main(String[] args) {

        // Passport having person as attribute
        Person person = new Person("Kedarnath", "Nagaradone", 21);
        PassportHasPerson pass1 = new PassportHasPerson("123456", "India", "2030", "2021", person);
        System.out.println(pass1);

        // Person having passport as attribute
        Passport personPass = new Passport("1234567890", "USA", "2050", "2008");
        PersonHasPassport personWithPass = new PersonHasPassport("Tony", "Stark", 48, personPass);
        System.out.println(personWithPass);


    }
}
