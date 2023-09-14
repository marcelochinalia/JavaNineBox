package com.api.ninebox.security.service.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid password.");
    }
}
