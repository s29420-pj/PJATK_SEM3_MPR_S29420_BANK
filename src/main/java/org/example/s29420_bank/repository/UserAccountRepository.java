package org.example.s29420_bank.repository;

import org.example.s29420_bank.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserAccountRepository {
    List<UserAccount> userAccountsList = new ArrayList<>();

    public UserAccount add(UserAccount userAccount) {
        userAccount.setId(userAccountsList.size() + 1);
        userAccountsList.add(userAccount);
        return userAccount;
    }

    public Optional<UserAccount> findById(Integer id) {
        return userAccountsList.stream()
                .filter(userAccount -> userAccount.getId().equals(id))
                .findFirst();
    }

    public List<UserAccount> findByBalanceGreaterThan(Double balance) {
        return userAccountsList.stream()
                .filter(userAccount -> userAccount.getBalance() > balance)
                .toList();
    }

    public void clear() {
        userAccountsList = new ArrayList<>();
    }
}
