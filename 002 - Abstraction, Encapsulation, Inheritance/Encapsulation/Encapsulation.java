package Encapsulation;
class SuperHero{
    private  String name;
    private String superPower;

    public void setNameSuperPower(String name, String superPower){
        this.name = name;
        this.superPower = superPower;
    }

    public void getSuperHeroDetails() { 
        System.out.println(name + " has " + superPower);
    }

    public void useSuperPower() {
        System.out.println(name + " uses " + superPower);
    }

    public void saveWorld() {
        System.out.println(name + " saves the world!");
    }
}

class SuperVillian{

    private String name;
    private String goal;
    private String power;

    public void createVillian(String name, String goal, String power) {
        this.name = name;
        this.goal = goal;
        this.power = power;
    }

    public void getVillianDetails() {
        System.out.println(name + " with power " + power + " wants to achieve " + goal);
    }

    public void wants() { 
        System.out.println(name + " wants to achieve " + goal);
    }

}

class Person{

    private String Fname;
    private String Lname;
    private int age;
    private String address;
    private String occupation;

    public Person(String Fname, String Lname, int age, String address, String occupation) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.age= age;
        this.address = address;
        this.occupation = occupation;
    }

    public void createPerson(String Fname, String Lname, int age, String address, String occupation) { 
        this.Fname = Fname;
        this.Lname = Lname;
        this.age = age;
        this.address = address;
        this.occupation = occupation;
    }

    public void getPersonDetails() {
        System.out.println(Fname + " " + Lname + " is " + age + " old, who works as " + occupation + " and lives at " + address);
    }

    public void eat(){
        System.out.println(Fname + " " + Lname +  " is eating.");
    }

    public void work() {
        System.out.println(Fname + " " + Lname + " works as " + occupation);
    }

    public void lives() {
        System.out.println(Fname + " " + Lname + " lives at " + address);
    }
}

class Book{
    private String title;
    private String author;
    private int pages;
    
    public void setBookDetails(String title, String author, int pages){
        this.title = title;
        this.author = author;
        this.pages = pages;
    } 

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public void getBookDetails(){
        System.out.println("I am currently reading " + title + " written by " + author + " and it has " + pages + " pages.");
    }

    public void read() { 
        System.out.println("I am reading the book " + title);
    }

    public void getSummary() {
        System.out.println("The book is " + title + " written by " + author + " and has " + pages + " pages.");
    }
}

class Movie{
    private String title;
    private String director;
    private int releaseYear;
    private String genre;

    public void createMovie(String title, String director, int releaseYear, String genre){
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public Movie(String title, String director, int releaseYear, String genre){
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public void getMovieDetails(){
        System.out.println("The movie " + this.title + " directed by " + director + " realeased in " + releaseYear + " belonging to genre " + genre);
    }
}

class Employee{
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary){
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public void createEmployee(String name, String position, double salary){
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public void getDetails(){
        System.out.println(name + " is working as " + position + " with salary" + salary);
    }

}

class Student{
    private String name;
    private String grade;
    private String school;
    
    public void createStudent(String name, String grade, String school) {
        this.name = name;
        this.grade = grade;
        this.school = school;
    }

    public Student(String name, String grade, String school) {
        this.name = name;
        this.grade = grade;
        this.school = school;
    }

    public void study(){
        System.out.println("The student " + this.name + " is studying in " + this.school);
    }

    public void getReportCard(){
        System.out.println("The student " + name + " has scored " + grade + " in the recent examinations");
    }

}

class Flight{
    private String airline;
    private String flightNumber;
    private String source;
    private String status;
    private String destination;
    private String tod;
    private String toa;
    private String arrivalDateTime;
    private String departureDateTime;
    
    public void createFlight(String airline, String flightNumber, String source, String destination, String toa, String tod, String arrivalDateTime, String departureDateTime, String status){
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.status = status;
        this.toa = toa;
        this.tod = tod;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }

    public Flight(String airline, String flightNumber, String source, String destination, String toa, String tod, String arrivalDateTime, String departureDateTime, String status){
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.status = status;
        this.toa = toa;
        this.tod = tod;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }

    public void getStatus() {
        System.out.println("The status of the flight number " + this.flightNumber + " from Airline " + this.airline + " is " + this.status + ", the current arrival time is " + this.arrivalDateTime + " and the current departure time is " + this.departureDateTime);
    }

    public void showDetails() {
        System.out.println(this.flightNumber + " from Ariline " + this.airline + " starting from " + this.source + " and going to " + this.destination + " and its current status is " + this.status + ", toa is " + toa + ", tod is " + tod);
    }
}


class Car{
    private String maker;
    private String model;
    private int topSpeed;

    public Car(String maker, String model, int topSpeed) {
        this.maker = maker;
        this.model = model;
        this.topSpeed = topSpeed;
    }

    public void start(){
        System.out.println("The car " + maker + " " + model + " has started.");
    }
    public void getDetails(){
        System.out.println("The car is " + maker + " " + model + " has top speed of " + topSpeed);
    }
}

class Laptop{

    private String brand;
    private String model;
    private String processor;
    private int ram;
    private int storage;
    
    public Laptop(String brand, String model, String processor, int ram, int storage){
        this.brand = brand;
        this.model = model;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    public void turnOn(){
        System.out.println("The laptop " + this.brand + " " + this.model + " has started");
    }

    public void turnOff() {
        System.out.println("The laptop " + this.brand + " " + this.model + " has turned off");
    }

    public void getDetails() {
        System.out.println("The laptop is of " + this.brand + " " + this.model + " has RAM of " + this.ram + " and has storage of " + this.storage + " with processor of " + this.processor);
    }

}

class Chair{
    private String material;
    private String color;
    private String height;

    public Chair(String material, String color, String height){
        this.material = material;
        this.color = color;
        this.height = height;
    }

    public void getHeight() {
        System.out.println("The chair is of height " + this.height);
    }

    public void getDetails() {
        System.out.println("The chair is of material " + this.material + " and it has color of " + this.color + " and it's height is "+ this.height);
    }

}

class Light{

    private float watts;
    private String brand;

    public Light(String brand, float watts){
        this.brand = brand;
        this.watts = watts;
    }
    
    public void turnOn(){
        System.out.println("The light " + this.brand + " has turned on");
    }

    public void turnOff() {
        System.out.println("The light " + this.brand + " has turned off");
    }

    public void getDetails() {
        System.out.println("The light is of watts " + this.watts + " and is of brand " + this.brand);
    }

}

class Doctor{
    private String name;
    private String specialization;
    private int experience; 
    
    public Doctor(String name, String specialization, int experience) {
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    public void treat(){
        System.out.println("The doctor " + this.name + " is treating a patient");
    }
    public void diagnose() {
        System.out.println("The doctor " + this.name + " is diagnosing a patient");
    }

    public void getDetails(){
        System.out.println("The doctor is " + this.name + " with specialization in " + this.specialization + " and has " + this.experience + " years of experience.");
    }
}

class Teacher{

    private String name;
    private String subject;
    private int experience;

    public Teacher(String name, String subject, int experience) {
        this.name = name;
        this.subject = subject;
        this.experience = experience;
    }
    
    public void teach(){
        System.out.println("The teacher " + this.name + " is teaching " + this.subject);
    }
    public void getDetails(){
        System.out.println("The teacher is " + this.name + " with subject " + this.subject + " and has " + this.experience + " years of experience.");
    }

}

class Artist{
    private String name;
    private String artStyle;
    private String medium;
    
    public Artist(String name, String artStyle, String medium) {
        this.name = name;
        this.artStyle = artStyle;
        this.medium = medium;
    }

    public void create(){
        System.out.println("The artist " + this.name + " is creating a piece of art in " + this.artStyle + " style using " + this.medium);
    }
    public void getDetails(){
        System.out.println("The artist is " + this.name + " with art style " + this.artStyle + " and medium " + this.medium);
    }
}

class Athlete{
    private String name;
    private String sport;
    private String team;
    
    public Athlete(String name, String sport, String team) {
        this.name = name;
        this.sport = sport;
        this.team = team;
    }

    public void train(){
        System.out.println(name + " is training for " + sport);
    }
    public void compete() {
        System.out.println(name + " is competing in " + sport);
    }
    public void getDetails(){
        System.out.println(name + " is an athlete playing " + sport + " for team " + team);
    }
}

class Hotel{

    private String name;
    private String location;
    private float rating;

    public Hotel(String name, String location, float rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }

    public void bookRoom(){
        System.out.println("Booking a room in " + this.name);
    }
    public void getDetails() {
        System.out.println("The hotel is " + this.name + " located at " + this.location + " with a rating of " + this.rating);
    }
}

class Restaurant{

    private String name;
    private String cuisine;
    private float rating;

    public Restaurant(String name, String cuisine, float rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
    }
    
    public void orderFood(){
        System.out.println("Ordering food from " + this.name);
    }
    public void getDetails() {
        System.out.println("The restaurant is " + this.name + " serving " + this.cuisine + " cuisine with a rating of " + this.rating);
    }
}

class Product {
    private String name;
    private float price;
    private String category;

    public Product(String name, float price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void getDetails() {
        System.out.println("The product is " + this.name + " with price " + this.price + " and belongs to category " + this.category);
    }
    public void applyDiscount() {
        System.out.println("Applying discount to the product " + this.name);
    }
}