package com.banquemisr.currencyconversionapp.exception;

/**
 * The BadEntryException class is a custom exception that extends the RuntimeException class and is
 * used to handle bad entries in a program.
 * @author Menna Moataz
 */
public class BadEntryException extends RuntimeException {
    public BadEntryException(String message) {
        super(message);
    }
}
