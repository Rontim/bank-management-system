package com.learning.bankapp.service;


import com.learning.bankapp.model.Account;
import com.learning.bankapp.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
   Customer saveCustomer(Customer customer);
   Customer getCustomerById(Long Id);
   Customer updateCustomer(Long Id, Customer customer);
   Customer patchCustomer(Long Id, Map<String, Object> updates);
   List<Customer> searchCustomers(String searchTerm);
   List<Account> getAccountsByCustomerId(Long customerId);
}
