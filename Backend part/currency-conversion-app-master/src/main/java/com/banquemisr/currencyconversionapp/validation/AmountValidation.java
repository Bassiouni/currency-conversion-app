package com.banquemisr.currencyconversionapp.validation;

import com.banquemisr.currencyconversionapp.exception.BadEntryException;
import org.springframework.stereotype.Component;

/**
 * The AmountValidation class is responsible for validating amount values.
 * @author Menna Moataz
 */
@Component
public class AmountValidation implements Validate<Double> {
    /**
     * Validates the given amount value.
     * @param amount The amount value to validate.
     * @throws BadEntryException if the amount is less than or equal to 0.
     */
    @Override
    public void validate(Double amount) {
        if (amount <= 0) {
            throw new BadEntryException("The amount should be more than 0");
        }
    }
}
