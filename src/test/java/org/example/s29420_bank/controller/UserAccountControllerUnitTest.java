package org.example.s29420_bank.controller;

import org.example.s29420_bank.model.UserAccount;
import org.example.s29420_bank.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.example.s29420_bank.model.Currency.PLN;
import static org.example.s29420_bank.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAccountControllerUnitTest {

    @Test
    void shouldReturnStatus201WhenUserAccountIsCreated() {
        UserAccountService userAccountService = mock(UserAccountService.class);
        UserAccountController userAccountController = new UserAccountController(userAccountService);
        UserAccount userAccount = new UserAccount(20, "Jan", "Kowalski", "12345678901", USD, 0.0);
        when(userAccountService.add(userAccount)).thenReturn(userAccount);

        ResponseEntity<UserAccount> response = userAccountController.add(userAccount);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void shouldReturnStatus200WhenUserAccountIsFoundById() {
        UserAccountService userAccountService = mock(UserAccountService.class);
        UserAccountController userAccountController = new UserAccountController(userAccountService);
        UserAccount userAccount = new UserAccount(20, "Jan", "Kowalski", "12345678901", PLN, 0.0);
        when(userAccountService.findById(1)).thenReturn(userAccount);

        ResponseEntity<UserAccount> response = userAccountController.findById(20);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldReturnStatus200WhenUserAccountsAreFoundByBalanceGreaterThan() {
        UserAccountService userAccountService = mock(UserAccountService.class);
        UserAccountController userAccountController = new UserAccountController(userAccountService);
        when(userAccountService.findByBalanceGreaterThan(1000.0)).thenReturn(Collections.emptyList());

        ResponseEntity<List<UserAccount>> response = userAccountController.findByBalanceGreaterThan(1000.0);

        assertEquals(200, response.getStatusCodeValue());
    }
}