package com.learning.bankapp.service.impl;

import com.learning.bankapp.exception.CustomerNotFoundException;
import com.learning.bankapp.model.Account;
import com.learning.bankapp.model.Customer;
import com.learning.bankapp.repository.AccountRepository;
import com.learning.bankapp.repository.CustomerRepository;
import com.learning.bankapp.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Account createAccount(Long customerId, Account account) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(customerId));
        account.setCustomer(existingCustomer);
        System.out.println(account);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByNumber(String accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);

        return account;
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }
}
