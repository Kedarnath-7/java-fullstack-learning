package com.northernarc.jpademo.service;

import com.northernarc.jpademo.model.DocumentVerification;
import com.northernarc.jpademo.model.Product;
import com.northernarc.jpademo.repository.DocumentVerificationRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DocumentVerificationServiceImpl implements DocumentVerificationService {

    private DocumentVerificationRepository documentVerificationRepository;

    public DocumentVerificationServiceImpl(DocumentVerificationRepository documentVerificationRepository){
        this.documentVerificationRepository = documentVerificationRepository;
    }

    @Override
    public DocumentVerification saveVerification(DocumentVerification documentVerification) {
        // validation logic
        return documentVerificationRepository.save(documentVerification);
    }

    @Override
    public DocumentVerification get(int id) {
        // validation logic
        return documentVerificationRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        // validation logic
        documentVerificationRepository.deleteById(id);
    }

    @Override
    public void update(int id, DocumentVerification documentVerification) {
        // validation logic
        DocumentVerification documentVerification1 = documentVerificationRepository.findById(id).get();
        documentVerification1.setApplicationId(documentVerification.getApplicationId());
        documentVerification1.setDocumentType(documentVerification.getDocumentType());
        documentVerification1.setDocumentNumber(documentVerification.getDocumentNumber());
        documentVerification1.setVerifierName(documentVerification.getVerifierName());
        documentVerification1.setVerifiedStatus(documentVerification.getVerifiedStatus());
        documentVerificationRepository.save(documentVerification1);
    }

    @Override
    public Collection<DocumentVerification> getAll() {
        // validation logic
        return documentVerificationRepository.findAll();
    }
}
