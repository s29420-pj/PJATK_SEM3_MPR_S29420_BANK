package org.example.s29420_bank.service;

import org.example.s29420_bank.exception.ValidationException;
import org.example.s29420_bank.model.UserAccount;
import org.example.s29420_bank.repository.UserAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.example.s29420_bank.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserAccountServiceTest {
    private static UserAccountService userAccountService;
    private static UserAccountRepository userAccountRepository;

    @BeforeAll
    static void setUp() {
        userAccountRepository = new UserAccountRepository();
        userAccountService = new UserAccountService(userAccountRepository);
    }

    @AfterEach
    void cleanUp() {
        userAccountRepository.clear();
    }

    @Test
    void shouldThrowExceptionWhenUserAccountFirstNameIsNull() {
        UserAccountService userAccountService = new UserAccountService(userAccountRepository);
        UserAccount userAccount = new UserAccount(null, "", "Kowalski", "12345678901", USD, 0.0);

        assertThrows(ValidationException.class, () -> userAccountService.add(userAccount));
    }

    @Test
    void shouldThrowExceptionWhenUserAccountLastNameIsNull() {
        UserAccountService userAccountService = new UserAccountService(userAccountRepository);
        UserAccount userAccount = new UserAccount(null, "Jan", "", "12345678901", USD, 0.0);

        assertThrows(ValidationException.class, () -> userAccountService.add(userAccount));
    }

    @Test
    void shouldThrowExceptionWhenUserAccountPESELIsNull() {
        UserAccountService userAccountService = new UserAccountService(userAccountRepository);
        UserAccount userAccount = new UserAccount(null, "Jan", "Kowalski", "", USD, 0.0);

        assertThrows(ValidationException.class, () -> userAccountService.add(userAccount));
    }
}