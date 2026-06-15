package day04.jdbcDao.dao;

import day04.jdbcDao.entity.DocumentVerification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DocumentVerificationDaoImpl implements DocumentVerificationDao {
    @Override
    public int saveVerification(DocumentVerification verification) {
        return 0;
    }

    @Override
    public DocumentVerification findByVerificationId(int verificationId) {
        return null;
    }

    @Override
    public Collection<DocumentVerification> findAllVerifications() {
        return List.of();
    }

    @Override
    public Collection<DocumentVerification> findByApplicationId(int applicationId) {
        return List.of();
    }

    @Override
    public Collection<DocumentVerification> findByDocumentType(String documentType) {
        return List.of();
    }

    @Override
    public Collection<DocumentVerification> findPendingVerifications() {
        return List.of();
    }

    @Override
    public Collection<DocumentVerification> findVerifiedDocuments() {
        return List.of();
    }

    @Override
    public Collection<DocumentVerification> findRejectedDocuments() {
        return List.of();
    }

    @Override
    public void verifyDocument(int verificationId, String verifierName, String remarks) {

    }

    @Override
    public void rejectDocument(int verificationId, String verifierName, String remarks) {

    }

    @Override
    public void deleteVerification(int verificationId) {

    }

    @Override
    public void deleteAllVerifications() {

    }

    @Override
    public Collection<DocumentVerification> sortByApplicantIdAsc() {
        return List.of();
    }

    @Override
    public Collection<DocumentVerification> sortByApplicantIdDesc() {
        return List.of();
    }

    @Override
    public DocumentVerification mapToVerification(ResultSet rs) throws SQLException {
        return null;
    }
}
