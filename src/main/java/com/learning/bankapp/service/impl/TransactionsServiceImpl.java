package com.learning.bankapp.service.impl;

import com.learning.bankapp.model.Account;
import com.learning.bankapp.model.Transactions;
import com.learning.bankapp.repository.TransactionRepository;
import com.learning.bankapp.service.AccountService;
import com.learning.bankapp.service.TransactionsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private AccountService accountService;

    private final TransactionRepository transactionRepository;

    public TransactionsServiceImpl(AccountService accountService, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transactions makeTransactions(String fromAccountNo, String toAccountNo, BigDecimal amount, String Description) {
        Account fromAccount = accountService.getAccountByNumber(fromAccountNo);
        Account toAccount = accountService.getAccountByNumber(toAccountNo);

        if (fromAccount == null || toAccount == null) {
            throw new EntityNotFoundException("One or both accounts not found.");

        }
        if(fromAccount.getBalance().compareTo(amount) < 0 ) {
            return null;
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        Transactions transactions = new Transactions();
        transactions.setAmount(amount);
        transactions.setDescription(Description);
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setToAccount(toAccount);
        transactions.setFromAccount(fromAccount);

        accountService.updateAccount(fromAccount);
        accountService.updateAccount(toAccount);

        return transactionRepository.save(transactions);
    }
}
