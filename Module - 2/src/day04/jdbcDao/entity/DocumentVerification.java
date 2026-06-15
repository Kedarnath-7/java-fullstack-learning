package day04.jdbcDao.entity;

public class DocumentVerification {
    private int verificationId;
    private int applicationId;
    private String documentType;
    private String documentNumber;
    private String verificationStatus;
    private String verifierName;
    private String remarks;

    public DocumentVerification() {

    }
    public DocumentVerification(int applicationId, String documentType, String documentNumber, String verificationStatus, String verifierName, String remarks) {
        this.applicationId = applicationId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.verificationStatus = verificationStatus;
        this.verifierName = verifierName;
        this.remarks = remarks;
    }
    public DocumentVerification(int verificationId, int applicationId, String documentType, String documentNumber, String verificationStatus, String verifierName, String remarks) {
        this.verificationId = verificationId;
        this.applicationId = applicationId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.verificationStatus = verificationStatus;
        this.verifierName = verifierName;
        this.remarks = remarks;
    }

    public int getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(int verificationId) {
        this.verificationId = verificationId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerifierName() {
        return verifierName;
    }

    public void setVerifierName(String verifierName) {
        this.verifierName = verifierName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Override
    public String toString() {
        return "Verification Id: " + this.verificationId + ", Application Id: " + this.applicationId + ", Verification status: " + this.verificationStatus + "}";
    }
}
