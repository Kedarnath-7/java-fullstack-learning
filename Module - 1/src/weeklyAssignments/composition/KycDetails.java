package weeklyAssignments.composition;

public class KycDetails {
    private String panNumber;
    private double aadharNumber;
    private boolean verified;

    public KycDetails(String panNumber, double aadharNumber, boolean verified) {
        this.panNumber = panNumber;
        this.aadharNumber = aadharNumber;
        this.verified = verified;
    }
    public String getPanNumber() {
        return panNumber;
    }
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
    public double getAadharNumber() {
        return aadharNumber;
    }
    public void setAadharNumber(double aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "panNumber=" + panNumber + ", verified=" + verified;
    }
}
