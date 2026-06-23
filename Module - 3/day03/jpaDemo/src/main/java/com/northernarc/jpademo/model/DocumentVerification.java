package com.northernarc.jpademo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_verification")
public class DocumentVerification {
    @Id
    @GeneratedValue
    private int verificationId;
    private int applicationId;
    private String documentType;
    private int documentNumber;
    private String verifierName;
    private String verifiedStatus;

    public DocumentVerification(){

    }

    public DocumentVerification(int applicationId, String documentType, int documentNumber, String verifiedName, String verifiedStatus){
        this.applicationId = applicationId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.verifierName = verifiedName;
        this.verifiedStatus = verifiedStatus;
    }

    public DocumentVerification(int verificationId, int applicationId, String documentType,
                                int documentNumber, String verifiedName, String verifiedStatus){
        this.verificationId = verificationId;
        this.applicationId = applicationId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.verifierName = verifiedName;
        this.verifiedStatus = verifiedStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getVerifierName() {
        return verifierName;
    }

    public void setVerifierName(String verifierName) {
        this.verifierName = verifierName;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    @Override
    public String toString(){
        return "Document Verification - {verification id: " + this.verificationId +
                ", application id: " + this.applicationId + ", document type: " + this.documentType +
                ", document number: " + this.documentNumber + ", verifier name: " + this.verifierName
                + ", verification status: " + this.verifiedStatus + "]";
    }
}
