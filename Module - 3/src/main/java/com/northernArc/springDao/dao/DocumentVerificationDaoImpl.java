package com.northernArc.springDao.dao;

import com.northernArc.springDao.connection.DBManager;
import com.northernArc.springDao.entity.DocumentVerification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class DocumentVerificationDaoImpl implements DocumentVerificationDao {
    @Override
    public int saveVerification(DocumentVerification verification) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into document_verification(application_id, document_type, document_number, verification_status, verifier_name, remarks) values(?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, verification.getApplicationId());
            stmt.setString(2, verification.getDocumentType());
            stmt.setString(3, verification.getDocumentNumber());
            stmt.setString(4, verification.getVerificationStatus());
            stmt.setString(5, verification.getVerifierName());
            stmt.setString(6, verification.getRemarks());
            return stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return 0;
    }

    @Override
    public DocumentVerification findByVerificationId(int verificationId) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification where verification_id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, verificationId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToVerification(rs);
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<DocumentVerification> findAllVerifications() {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public Collection<DocumentVerification> findByApplicationId(int applicationId) {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification where application_id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, applicationId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public Collection<DocumentVerification> findByDocumentType(String documentType) {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification where document_type=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, documentType);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public Collection<DocumentVerification> findPendingVerifications() {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification where verification_status='pending';";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public Collection<DocumentVerification> findVerifiedDocuments() {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification where verification_status='verified';";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public Collection<DocumentVerification> findRejectedDocuments() {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification where verification_status='rejected';";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public void verifyDocument(int verificationId, String verifierName, String remarks) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update document_verification set verification_status='verified', verifier_name=?, remarks=? where verification_id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, verifierName);
            stmt.setString(2, remarks);
            stmt.setInt(3, verificationId);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void rejectDocument(int verificationId, String verifierName, String remarks) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update document_verification set verification_status='rejected', verifier_name=?, remarks=? where verification_id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, verifierName);
            stmt.setString(2, remarks);
            stmt.setInt(3, verificationId);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void deleteVerification(int verificationId) {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from document_verification where verification_id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, verificationId);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void deleteAllVerifications() {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from document_verification;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public Collection<DocumentVerification> sortByApplicantIdAsc() {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification order by application_id asc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public Collection<DocumentVerification> sortByApplicantIdDesc() {
        Collection<DocumentVerification> verifications = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from document_verification order by application_id desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                verifications.add(mapToVerification(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return verifications;
    }

    @Override
    public DocumentVerification mapToVerification(ResultSet rs) throws SQLException {
        return new DocumentVerification(
                rs.getInt("verification_id"),
                rs.getInt("application_id"),
                rs.getString("document_type"),
                rs.getString("document_number"),
                rs.getString("verification_status"),
                rs.getString("verifier_name"),
                rs.getString("remarks")
        );
    }
}
