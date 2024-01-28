package org.example.s29420_bank.repository;

import org.example.s29420_bank.model.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.example.s29420_bank.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.*;

class UserAccountRepositoryTest {
    private static UserAccountRepository userAccountRepository;

    @BeforeAll
    public static void setUp() {
        userAccountRepository = new UserAccountRepository();
    }

    @AfterEach
    public void cleanUp() {
        userAccountRepository.clear();
    }

    @Test
    void shouldCorrectlyAddUserAccount() {
        UserAccount userAccount = new UserAccount(null, "Jan", "Kowalski", "12345678901", USD, 0.0);
        UserAccount result = assertDoesNotThrow(() -> userAccountRepository.add(userAccount));

        assertEquals(userAccount.getFirstName(), result.getFirstName());
        assertNotNull(result.getId());
    }

    @Test
    void shouldFindById() {
        UserAccount userAccount = new UserAccount(5, "Jan", "Kowalski", "12345678901", USD, 0.0);
        UserAccount addedUserAccount = userAccountRepository.add(userAccount);

        UserAccount result = assertDoesNotThrow(() -> userAccountRepository.findById(addedUserAccount.getId()).get());

        assertEquals(addedUserAccount.getId(), result.getId());
        assertEquals(addedUserAccount.getFirstName(), result.getFirstName());
        assertEquals(addedUserAccount.getLastName(), result.getLastName());
        assertEquals(addedUserAccount.getPESEL(), result.getPESEL());
        assertEquals(addedUserAccount.getCurrency(), result.getCurrency());
        assertEquals(addedUserAccount.getBalance(), result.getBalance());
    }

    @Test
    void shouldFindByBalanceGreaterThan() {
        UserAccount userAccount1 = new UserAccount(5, "Jan", "Kowalski", "12345678901", USD, 0.0);
        UserAccount userAccount2 = new UserAccount(6, "Jan", "Kowalski", "12345678901", USD, 100.0);
        UserAccount userAccount3 = new UserAccount(7, "Jan", "Kowalski", "12345678901", USD, 200.0);
        UserAccount userAccount4 = new UserAccount(8, "Jan", "Kowalski", "12345678901", USD, 300.0);
        UserAccount userAccount5 = new UserAccount(9, "Jan", "Kowalski", "12345678901", USD, 400.0);
        UserAccount userAccount6 = new UserAccount(10, "Jan", "Kowalski", "12345678901", USD, 500.0);

        userAccountRepository.add(userAccount1);
        userAccountRepository.add(userAccount2);
        userAccountRepository.add(userAccount3);
        userAccountRepository.add(userAccount4);
        userAccountRepository.add(userAccount5);
        userAccountRepository.add(userAccount6);

        assertEquals(4, userAccountRepository.findByBalanceGreaterThan(100.0).size());
    }

}