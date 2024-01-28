package org.example.s29420_bank.service;

import lombok.AllArgsConstructor;
import org.example.s29420_bank.exception.ValidationException;
import org.example.s29420_bank.model.UserAccount;
import org.example.s29420_bank.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount add(UserAccount userAccount) {
        if (userAccount.getFirstName() == null || userAccount.getFirstName().isEmpty()) {
            throw new ValidationException("User firstName cannot be empty");
        }
        if (userAccount.getLastName() == null || userAccount.getLastName().isEmpty()) {
            throw new ValidationException("User lastName cannot be empty");
        }
        if (userAccount.getPESEL() == null || userAccount.getPESEL().length() != 11) {
            throw new ValidationException("User PESEL cannot be empty and must have 11 digits");
        }
        if (userAccount.getBalance() == null || userAccount.getBalance() < 0) {
            throw new ValidationException("User balance value cannot be negative");
        }
        if (userAccount.getCurrency() == null) {
            throw new ValidationException("User currency cannot be null and must be PLN/USD/EUR");
        }

        return userAccountRepository.add(userAccount);
    }

    public UserAccount findById(Integer id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ValidationException("User with id " + id + " not found"));
    }

    public List<UserAccount> findByBalanceGreaterThan(Double balance) {
        List<UserAccount> byBalanceGreaterThan = userAccountRepository.findByBalanceGreaterThan(balance);
        if (byBalanceGreaterThan.isEmpty()) {
            throw new ValidationException("Users with balance " + balance + " and greater not found");
        }

        return byBalanceGreaterThan;
    }
}
