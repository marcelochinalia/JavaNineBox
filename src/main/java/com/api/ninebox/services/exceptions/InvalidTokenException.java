package com.api.ninebox.services.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(){
        super("Token invalid or expired");
    }
}
