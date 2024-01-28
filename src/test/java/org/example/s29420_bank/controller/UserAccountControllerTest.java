package org.example.s29420_bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.s29420_bank.model.UserAccount;
import org.example.s29420_bank.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.example.s29420_bank.model.Currency.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
class UserAccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void cleanUp() {
        userAccountRepository.clear();
    }

    @Test
    void shouldCorrectlyAddUserAccount() {
        UserAccount userAccount = new UserAccount(null, "Jan", "Kowalski", "12345678901", USD, 0.0);
        UserAccount result = webTestClient.post()
                .uri("/userAccount/add")
                .bodyValue(userAccount)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserAccount.class)
                .returnResult()
                .getResponseBody();

        assertEquals(userAccount.getFirstName(), result.getFirstName());
        assertNotNull(result.getId());
    }

    @Test
    void shouldCorrectlyFindUserAccountById() {
        UserAccount userAccount = new UserAccount(null, "Jan", "Kowalski", "12345678901", USD, 0.0);
        UserAccount addedUserAccount = userAccountRepository.add(userAccount);

        UserAccount result = webTestClient.get()
                .uri("/userAccount/findById/" + addedUserAccount.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserAccount.class)
                .returnResult()
                .getResponseBody();

        assertEquals(addedUserAccount.getId(), result.getId());
        assertEquals(addedUserAccount.getFirstName(), result.getFirstName());
        assertEquals(addedUserAccount.getLastName(), result.getLastName());
        assertEquals(addedUserAccount.getPESEL(), result.getPESEL());
        assertEquals(addedUserAccount.getCurrency(), result.getCurrency());
        assertEquals(addedUserAccount.getBalance(), result.getBalance());
    }

    @Test
    void shouldCorrectlyFindUserAccountByBalanceGreaterThan() {
        UserAccount userAccount1 = new UserAccount(5, "Jan", "Kowalski", "12345678901", USD, 0.0);
        UserAccount userAccount2 = new UserAccount(6, "Marek", "Lewańczyk", "12345678901", USD, 100.0);
        UserAccount userAccount3 = new UserAccount(7, "Andrzej", "Robocik", "12345678901", USD, 200.0);
        UserAccount userAccount4 = new UserAccount(8, "Robert", "Mały", "12345678901", USD, 300.0);
        UserAccount userAccount5 = new UserAccount(9, "Adam", "Jakiś", "12345678901", USD, 400.0);
        UserAccount userAccount6 = new UserAccount(10, "Janusz", "Januszowy", "12345678901", USD, 500.0);

        userAccountRepository.add(userAccount1);
        userAccountRepository.add(userAccount2);
        userAccountRepository.add(userAccount3);
        userAccountRepository.add(userAccount4);
        userAccountRepository.add(userAccount5);
        userAccountRepository.add(userAccount6);

        UserAccount[] result = webTestClient.get()
                .uri("/userAccount/findByBalanceGreaterThan-100.0")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserAccount[].class)
                .returnResult()
                .getResponseBody();

        assertEquals(4, result.length);
    }
}