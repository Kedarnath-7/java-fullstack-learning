package com.northernArc.springDao.dao;

import com.northernArc.springDao.entity.DocumentVerification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface DocumentVerificationDao {
    int saveVerification(DocumentVerification verification);
    DocumentVerification findByVerificationId(int verificationId);
    Collection<DocumentVerification> findAllVerifications();
    Collection<DocumentVerification> findByApplicationId(int applicationId);
    Collection<DocumentVerification> findByDocumentType(String documentType);
    Collection<DocumentVerification> findPendingVerifications();
    Collection<DocumentVerification> findVerifiedDocuments();
    Collection<DocumentVerification> findRejectedDocuments();
    void verifyDocument(int verificationId, String verifierName, String remarks);
    void rejectDocument(int verificationId, String verifierName, String remarks);
    void deleteVerification(int verificationId);
    void deleteAllVerifications();
    Collection<DocumentVerification> sortByApplicantIdAsc();
    Collection<DocumentVerification> sortByApplicantIdDesc();
    DocumentVerification mapToVerification(ResultSet rs) throws SQLException;
}
