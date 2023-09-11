package com.banquemisr.currencyconversionapp.validation;

/**
 * The Validate interface defines a contract for validation operations.
 * @param <T> The type of value to validate.
 * @author Menna Moataz
 */
public interface Validate<T> {
    /**
     * Validates the given value.
     * @param value The value to validate.
     */
    void validate(T amount);
}
