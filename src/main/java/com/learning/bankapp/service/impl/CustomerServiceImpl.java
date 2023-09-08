package com.learning.bankapp.service.impl;

import com.learning.bankapp.exception.CustomerNotFoundException;
import com.learning.bankapp.model.Account;
import com.learning.bankapp.model.Customer;
import com.learning.bankapp.repository.AccountRepository;
import com.learning.bankapp.repository.CustomerRepository;
import com.learning.bankapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long Id) {
        Customer customer = customerRepository.findById(Id).orElseThrow(()-> new CustomerNotFoundException(Id));
        Account account = accountRepository.findByCustomer(customer);
        System.out.println(account);
        return customer;
    }

    @Override
    public Customer updateCustomer(Long Id, Customer customer) {
        if (customerRepository.existsById(Id)) {
            customer.setCustomerId(Id);
            return customerRepository.save(customer);
        } else {
            throw new CustomerNotFoundException(Id);
        }
    }

    @Override
    public Customer patchCustomer(Long Id, Map<String, Object> updates) {
        Customer patchCustomer = customerRepository.findById(Id).orElseThrow(
                ()-> new CustomerNotFoundException(Id)
        );

        if (updates.containsKey("firstName")) {
            patchCustomer.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")) {
            patchCustomer.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("email")) {
            patchCustomer.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("dateOfBirth")) {
            patchCustomer.setDateOfBirth((LocalDate) updates.get("dateOfBirth"));
        }
        if (updates.containsKey("address")) {
            patchCustomer.setAddress((String) updates.get("address"));
        }
        if (updates.containsKey("contact")) {
            patchCustomer.setContact((String) updates.get("contact"));
        }

        return customerRepository.save(patchCustomer);
    }

    @Override
    public List<Customer> searchCustomers(String searchTerm) {
        return customerRepository.searchTerm(searchTerm.toLowerCase());
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException(customerId));

        return customer.getAccounts();
    }
}
