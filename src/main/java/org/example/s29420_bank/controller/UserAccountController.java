package org.example.s29420_bank.controller;

import lombok.RequiredArgsConstructor;
import org.example.s29420_bank.model.UserAccount;
import org.example.s29420_bank.service.UserAccountService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userAccount")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/add")
    public ResponseEntity<UserAccount> add(@RequestBody UserAccount userAccount) {
        UserAccount addedUserAccount = userAccountService.add(userAccount);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(addedUserAccount);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserAccount> findById(@PathVariable Integer id) {
        UserAccount userAccount = userAccountService.findById(id);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(userAccount);
    }

    @GetMapping("/findByBalanceGreaterThan-{balance}")
    public ResponseEntity<List<UserAccount>> findByBalanceGreaterThan(@PathVariable Double balance) {
        List<UserAccount> userAccount = userAccountService.findByBalanceGreaterThan(balance);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(userAccount);
    }
}
