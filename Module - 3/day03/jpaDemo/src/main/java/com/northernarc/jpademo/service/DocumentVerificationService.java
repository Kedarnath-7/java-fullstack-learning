package com.northernarc.jpademo.service;

import com.northernarc.jpademo.model.Book;
import com.northernarc.jpademo.model.DocumentVerification;
import com.northernarc.jpademo.model.Product;

import java.util.Collection;

public interface DocumentVerificationService {
    DocumentVerification saveVerification(DocumentVerification documentVerification);
    DocumentVerification get(int id);
    void delete(int id);
    void update(int id, DocumentVerification documentVerification);
    Collection<DocumentVerification> getAll();
}
