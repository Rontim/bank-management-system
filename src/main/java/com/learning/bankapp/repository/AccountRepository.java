package com.learning.bankapp.repository;

import com.learning.bankapp.model.Account;
import com.learning.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.customer LIKE %:customer%")
    Account findByCustomer(@Param("customer") Customer customer);

    @Query("SELECT a FROM Account a WHERE a.accountNumber LIKE %:accountNo%")
    Account findByAccountNo(@Param("accountNo") String accountNumber);
}
