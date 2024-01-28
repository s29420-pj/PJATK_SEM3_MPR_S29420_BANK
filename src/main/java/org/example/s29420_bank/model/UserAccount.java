package org.example.s29420_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAccount {
    private Integer id;
    private String firstName;
    private String lastName;
    private String PESEL;
    private Currency currency;
    private Double balance;
}
