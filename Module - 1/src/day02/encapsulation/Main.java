package day02.encapsulation;

class Main{
    public static void main(String[] args) {
        
        SuperHero hero1 = new SuperHero();
        hero1.setNameSuperPower("Iron Man", "Smartest");

        hero1.useSuperPower();
        hero1.saveWorld();

        SuperHero hero2 = new SuperHero();
        hero2.setNameSuperPower("Spider Man", "Web Shooting");

        hero2.useSuperPower();
        hero2.saveWorld();


        SuperVillian villian1 = new SuperVillian();
        villian1.createVillian("Thanos", "Destroy half of the universe", "Mind Control");

        villian1.wants();

        Person person1 = new Person("Kedarnath", "Nagaradone", 22, "Hotel Dhans", "Associate");
        // person1.createPerson("Kedarnath", "Nagaradone", 22, "Hotel Dhans", "Associate Technology");

        person1.getPersonDetails();
        

        person1.eat();
        person1.work();
        person1.lives();

        Book book1 = new Book("Parallel World", "Michio kaku", 300);
        // book1.setBookDetails("Parallel Worlds", "Michio Kaku", 300);
        book1.getBookDetails();

        book1.read();
        book1.getSummary();

        Movie movie1 = new Movie("Salaar", "Prashant Neel", 2022, "Drama");
        // movie1.createMovie("Salaar", "Prashant Neel", 2022, "Drama");
        movie1.getMovieDetails();

        Employee employee1 = new Employee("Kedar", "Associate", 0);
        // employee1.createEmployee("Kedar", "Associate", 0);
        employee1.getDetails();

        Student stud1 = new Student("Kedar", "A", "SRMIST");
        // stud1.createStudent("Kedar", "A", "SRMIST");
        stud1.getReportCard();
        stud1.study();

        Flight flight1 = new Flight("Indigo", "A123FH", "Hyderabad", "Chennai", null, null, null, null, null);
        // flight1.createFlight("Indigo", "A123FH", "Hyderabad", "Chennai", null, null, null, null, null);
        flight1.getStatus();
        flight1.showDetails();

        Car car1 = new Car("Tesla", "Model S", 200);
        car1.getDetails();
        car1.start();
        
        Laptop laptop1 = new Laptop("Dell", "14 Pro", "Intel i5", 16, 512);
        laptop1.getDetails();
        laptop1.turnOn();
        laptop1.turnOff();

        Chair chair1 = new Chair("Wood", "Brown", "48 inches");
        chair1.getDetails();
        chair1.getHeight();

        Light light1 = new Light("Philips", 60.0f);
        light1.getDetails();
        light1.turnOn();
        light1.turnOff();
        

        Doctor doctor1 = new Doctor("Dr. Smith", "Cardiologist", 10);
        doctor1.getDetails();
        doctor1.diagnose();
        doctor1.treat();

        Teacher teacher1 = new Teacher("Mr. Johnson", "Mathematics", 5);
        teacher1.getDetails();
        teacher1.teach();   

        Artist artist1 = new Artist("Leonardo da Vinci", "Everything", "Mona Lisa");
        artist1.getDetails();
        artist1.create();

        Athlete athlete1 = new Athlete("Dhoni", "Cricket", "CSK");
        athlete1.getDetails();
        athlete1.train();
        athlete1.compete();
        
        Hotel hotel1 = new Hotel("Grand Palace", "5-star", 200);
        hotel1.getDetails();
        hotel1.bookRoom();

        Restaurant restaurant1 = new Restaurant("Food Haven", "Italian", 50);
        restaurant1.getDetails();
        restaurant1.orderFood();

        Product product1 = new Product("Iphone", 120000, "Mobile");
        product1.getDetails();
        product1.applyDiscount();

    
    }
}