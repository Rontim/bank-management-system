package com.learning.bankapp.controller;

import com.learning.bankapp.model.Account;
import com.learning.bankapp.model.Customer;
import com.learning.bankapp.model.Transactions;
import com.learning.bankapp.service.AccountService;
import com.learning.bankapp.service.CustomerService;
import com.learning.bankapp.service.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/bms/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;

    private final TransactionsService transactionsService;

    public CustomerController(CustomerService customerService, AccountService accountService, TransactionsService transactionsService) {
        super();
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionsService = transactionsService;
    }

    @PostMapping
    ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("{Id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(name = "Id") Long Id) {
        Customer customer = customerService.getCustomerById(Id);
        return new ResponseEntity<>(customer, HttpStatus.FOUND);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable(name = "Id") Long Id, @RequestBody Customer customer) {
        Customer updatedcustomer = customerService.updateCustomer(Id, customer);
        if (updatedcustomer != null) {
            return new ResponseEntity<>(updatedcustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<Customer> patchCustomer(@PathVariable(name = "Id") Long Id,
            @RequestBody Map<String, Object> updates) {
        Customer patchedCustomer = customerService.patchCustomer(Id, updates);
        if (patchedCustomer != null) {
            return new ResponseEntity<>(patchedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam("q") String searchTerm) {
        List<Customer> matchingCustomers = customerService.searchCustomers(searchTerm);
        return new ResponseEntity<>(matchingCustomers, HttpStatus.OK);
    }

    @PostMapping("/{Id}/accounts")
    public ResponseEntity<Account> createAccount(@PathVariable(name = "Id") Long customerId,
            @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(customerId, account);
        return new ResponseEntity<Account>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{Id}/accounts/")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable(name = "Id") Long customerId) {
        List<Account> accounts = customerService.getAccountsByCustomerId(customerId);

        return new ResponseEntity<>(accounts, HttpStatus.FOUND);
    }

    @PostMapping("/transact")
    public ResponseEntity<Transactions> makeTransaction(@RequestBody Map<String, Object> transactionDetails){
        String fromAccountNo = transactionDetails.get("fromAccount").toString();
        String toAccountNo = transactionDetails.get("toAccount").toString();
        BigDecimal amount = new BigDecimal(transactionDetails.get("amount").toString());
        String description = transactionDetails.get("description").toString();

        Transactions transaction = transactionsService.makeTransactions(fromAccountNo, toAccountNo, amount, description);

        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }

}
