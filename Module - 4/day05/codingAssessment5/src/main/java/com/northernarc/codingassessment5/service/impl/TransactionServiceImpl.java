package com.northernarc.codingassessment5.service.impl;

import com.northernarc.codingassessment5.model.Transaction;
import com.northernarc.codingassessment5.repository.TransactionRepository;
import com.northernarc.codingassessment5.service.TransactionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return null;
    }
}
