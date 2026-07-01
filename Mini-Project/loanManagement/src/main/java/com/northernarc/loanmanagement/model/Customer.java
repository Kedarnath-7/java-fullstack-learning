package com.northernarc.loanmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Customer name cannot be empty")
    @Column(nullable = false)
    private String customerName;

    @Email
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "branch cannot be empty")
    private String branch;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoanAccount> loanAccounts = new ArrayList<>();
}
