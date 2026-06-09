package day01.associationExamples.aggregation;

public class Main {
    public static void main(String[] args) {

        // IT Dept
        Department itDept = new Department("Technology", "Chennai-II");
        itDept.addEmployee(new Employee("Kedarnath"));
        itDept.addEmployee(new Employee("Tony"));
        itDept.addEmployee(new Employee("Peter"));
        itDept.addEmployee(new Employee("Thanos"));
        System.out.println(itDept.getEmployees());
        itDept.removeEmployee(new Employee("Thanos"));
        System.out.println(itDept.getEmployees());


        // HR Dept
        Department hrDept = new Department("HR", "Mumbai");
        hrDept.addEmployee(new Employee("Rasika"));
        hrDept.addEmployee(new Employee("Archana"));
        hrDept.addEmployee(new Employee("Monish"));
        System.out.println(hrDept.getEmployees());
        hrDept.removeEmployee(new Employee("Monish"));
        System.out.println(hrDept.getEmployees());


        // Sales Dept
        Department salesDept = new Department("Sales", "Gurgaon");
        salesDept.addEmployee(new Employee("Jordan"));
        salesDept.addEmployee(new Employee("Peter"));
        salesDept.addEmployee(new Employee("Tony"));
        System.out.println(salesDept.getEmployees());
        salesDept.removeEmployee(new Employee("Peter"));
        System.out.println(salesDept.getEmployees());



        // Team and Player Examples

        // IPL
        Team csk = new Team("India", "IPL");
        csk.addPlayer(new Player("Ms Dhoni"));
        csk.addPlayer(new Player("Sanju Samson"));
        csk.addPlayer(new Player("Ayush Mahtre"));
        csk.addPlayer(new Player("Rahul Tripati"));
        System.out.println(csk.getPlayers());
        csk.removePlayer(new Player("Rahul Tripati"));
        System.out.println(csk.getPlayers());

        // BBL
        Team syndeySixers = new Team("Syndey Sixers", "BBL");
        syndeySixers.addPlayer(new Player("Steve Smith"));
        syndeySixers.addPlayer(new Player("Babar Azam"));
        syndeySixers.addPlayer(new Player("Nathan Ellis"));
        syndeySixers.addPlayer(new Player("David Warner"));
        System.out.println(syndeySixers.getPlayers());
        syndeySixers.removePlayer(new Player("Babar Azam"));
        System.out.println(syndeySixers.getPlayers());


        // under writer and loan applications/applicants


    }
}
