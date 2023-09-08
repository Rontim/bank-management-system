package com.learning.bankapp.service;

import com.learning.bankapp.model.Transactions;

import java.math.BigDecimal;

public interface TransactionsService {
    Transactions makeTransactions(String fromAccountNo, String toAccountNo, BigDecimal amount, String Description);
}
