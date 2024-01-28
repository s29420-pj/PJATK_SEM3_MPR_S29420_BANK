package org.example.s29420_bank.repository;

import org.apache.catalina.User;
import org.example.s29420_bank.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserAccountRepository {
    List<UserAccount> userAccountsList = new ArrayList<>();

    public UserAccount add(UserAccount userAccount) {
        userAccountsList.add(userAccount);
        return userAccount;
    }

    public Optional<UserAccount> findById(Integer id) {
        return userAccountsList.stream()
                .filter(userAccount -> userAccount.getId().equals(id))
                .findFirst();
    }


}
