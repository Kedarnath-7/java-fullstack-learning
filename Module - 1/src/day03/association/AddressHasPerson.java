package day03.association;

public class AddressHasPerson {
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String zip;

    public AddressHasPerson(String houseNo, String street, String city, String state, String zip) {
        this.houseNo = houseNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String toString() {
        return " " + houseNo + " " + street + " " + city + " " + state + " " + zip;
    }
}
