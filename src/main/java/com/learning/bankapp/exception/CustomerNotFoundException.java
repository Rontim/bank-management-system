package com.learning.bankapp.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long Id) {
        super("Customer not found with id: " + Id);
    }
}
