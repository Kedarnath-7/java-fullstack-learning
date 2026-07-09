package com.northernarc.codingassessment5.service.impl;

import com.northernarc.codingassessment5.dto.DepositRequest;
import com.northernarc.codingassessment5.dto.TransferRequest;
import com.northernarc.codingassessment5.dto.WithdrawRequest;
import com.northernarc.codingassessment5.model.Account;
import com.northernarc.codingassessment5.repository.AccountRepository;
import com.northernarc.codingassessment5.repository.TransactionRepository;
import com.northernarc.codingassessment5.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public Account getAccountById(Long id) {
        return null;
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
    }

    @Override
    public Account deposit(DepositRequest request) {
        return null;
    }

    @Override
    public Account withdraw(WithdrawRequest request) {
        return null;
    }

    @Override
    public void transfer(TransferRequest request) {
    }
}
