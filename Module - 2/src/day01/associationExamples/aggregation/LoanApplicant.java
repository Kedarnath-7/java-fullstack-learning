package day01.associationExamples.aggregation;

public class LoanApplicant {
    private String firstName;
    private String lastName;
    private String applicantId;

    public LoanApplicant(String firstName, String lastName, String applicantId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.applicantId = applicantId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
