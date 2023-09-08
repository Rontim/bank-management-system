package com.learning.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerId;

    @Column( name = "last_name")
    @NotNull
    private String lastName;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column( name = "first_name")
    @NotNull
    private String firstName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String address;

    public Customer(@NotNull String lastName, @NotNull String email, @NotNull String firstName, @NotNull LocalDate dateOfBirth, @NotNull String address, @NotNull String contact, List<Account> accounts) {
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contact = contact;
        this.accounts = accounts;
    }

    @NotNull
    private String contact;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    protected Customer() {}


}
