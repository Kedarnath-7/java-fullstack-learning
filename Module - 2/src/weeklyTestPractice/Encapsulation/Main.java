package weeklyTestPractice.Encapsulation;

public class Main {
    public static void main(String[] args) {
        Branch newBranch = new Branch("SB001", "Chennai");
        Employee e1 = new Employee("EM001", "Kedar", 50000);
        newBranch.addEmployee(e1);
        newBranch.displayBrancDetails();
        e1.increaseSalary(15.4);
        newBranch.displayBrancDetails();

    }
}
