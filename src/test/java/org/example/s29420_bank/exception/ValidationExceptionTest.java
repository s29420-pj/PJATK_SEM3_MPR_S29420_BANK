package org.example.s29420_bank.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationExceptionTest {
    @Test
    void shouldThrowException() {
        ValidationException validationException = new ValidationException("Test Message");
        assertEquals("Test Message", validationException.getMessage());
    }
}