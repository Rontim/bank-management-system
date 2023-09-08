
package com.learning.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer")
    @NotNull
    @JsonIgnore
    private Customer customer;

    @Column(unique = true)
    @NotNull
    private String accountNumber;

    @Column
    private BigDecimal balance;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transactions> outgoingTransactions;

    @OneToMany(mappedBy = "toAccount")
    private List<Transactions> inComingTransactions;



    protected Account() {
    }

    public Account(@NotNull Customer customer, @NotNull String accountNumber, BigDecimal balance,
            List<Transactions> outgoingTransactions, List<Transactions> inComingTransactions) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.outgoingTransactions = outgoingTransactions;
        this.inComingTransactions = inComingTransactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber +
                '}';
    }
}
