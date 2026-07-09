package com.northernarc.codingassessment5.service;

import com.northernarc.codingassessment5.model.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    List<Transaction> getTransactionsByAccountId(Long accountId);
}
