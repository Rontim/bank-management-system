package com.learning.bankapp.service;

import com.learning.bankapp.model.Account;

public interface AccountService {
    Account createAccount(Long customerId, Account account);
    Account getAccountByNumber(String accountNo);

    void updateAccount(Account account);

}
