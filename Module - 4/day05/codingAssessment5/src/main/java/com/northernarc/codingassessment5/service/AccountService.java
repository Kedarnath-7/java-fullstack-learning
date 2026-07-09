package com.northernarc.codingassessment5.service;

import com.northernarc.codingassessment5.dto.DepositRequest;
import com.northernarc.codingassessment5.dto.TransferRequest;
import com.northernarc.codingassessment5.dto.WithdrawRequest;
import com.northernarc.codingassessment5.model.Account;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account updateAccount(Long id, Account account);
    void deleteAccount(Long id);
    Account deposit(DepositRequest request);
    Account withdraw(WithdrawRequest request);
    void transfer(TransferRequest request);
}
