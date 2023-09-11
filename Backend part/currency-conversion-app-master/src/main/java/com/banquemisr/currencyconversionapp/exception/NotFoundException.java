package com.banquemisr.currencyconversionapp.exception;

/**
 * The NotFoundException class is a custom exception that extends the RuntimeException class and is
 * used to indicate that a certain item or resource was not found.
 * @author Menna Moataz
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
