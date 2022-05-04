package com.example.coursebooking.exception;

public class AccountAlreadyExistsException extends AppBasicException{

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
