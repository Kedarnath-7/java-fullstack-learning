package javaAssociation.association;

public class PassportHasPerson {
    private String passportNo;
    private String country;
    private String expiry;
    private String issueDate;
    private Person person;

    public PassportHasPerson(String passportNo, String country, String expiry, String issueDate) {
        this.passportNo = passportNo;
        this.country = country;
        this.expiry = expiry;
        this.issueDate = issueDate;
    }

    public PassportHasPerson(String passportNo, String country, String expiry, String issueDate, Person person) {
        this.passportNo = passportNo;
        this.country = country;
        this.expiry = expiry;
        this.issueDate = issueDate;
        this.person = person;
    }
    public String getPassportNo() {
        return passportNo;
    }
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getExpiry() {
        return expiry;
    }
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public Person getPerson() {
        return person;
    }

    public String toString(){
        return " " + passportNo + " " + country + " " + expiry + " " + issueDate + " " + person.toString();
    }

}
