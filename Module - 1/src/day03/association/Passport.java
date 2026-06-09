package day03.association;

public class Passport {
    private String passportNo;
    private String country;
    private String expiry;
    private String issueDate;

    public Passport(String passportNo, String country, String expiry, String issueDate) {
        this.passportNo = passportNo;
        this.country = country;
        this.expiry = expiry;
        this.issueDate = issueDate;
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


    public String toString(){
        return " " + passportNo + " " + country + " " + expiry + " " + issueDate + " ";
    }
}
