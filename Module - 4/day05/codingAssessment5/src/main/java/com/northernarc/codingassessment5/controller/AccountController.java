package com.northernarc.codingassessment5.controller;

import com.northernarc.codingassessment5.dto.DepositRequest;
import com.northernarc.codingassessment5.dto.TransferRequest;
import com.northernarc.codingassessment5.dto.WithdrawRequest;
import com.northernarc.codingassessment5.model.Account;
import com.northernarc.codingassessment5.model.Transaction;
import com.northernarc.codingassessment5.service.AccountService;
import com.northernarc.codingassessment5.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody Account account) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody DepositRequest request) {
        return null;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestBody WithdrawRequest request) {
        return null;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest request) {
        return null;
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long id) {
        return null;
    }
}
