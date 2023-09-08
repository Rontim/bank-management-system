package com.learning.bankapp.repository;

import com.learning.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE %:searchTerm% OR LOWER(c.lastName) LIKE %:searchTerm% OR LOWER(c.email) LIKE %:searchTerm%")
    List<Customer> searchTerm(@Param("searchTerm") String searchTerm);
}
