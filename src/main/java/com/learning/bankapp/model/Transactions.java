
package com.learning.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "from_account")
    @NotNull
    @JsonIgnore
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account")
    @NotNull
    @JsonIgnore
    private Account toAccount;

    @Column
    @NotNull
    private BigDecimal amount;

    @Column
    @NotNull
    private String description;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
}

