package com.banquemisr.currencyconversionapp.validation;

import com.banquemisr.currencyconversionapp.exception.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Validate whether a currency code exists in a given list
 * @author Muhammad Bassiouni
 * @author Menna Moataz
 */
@Getter
@Component
public class CurrencyExistsValidation implements Validate<String> {
    @Setter
    private List<String> currencyDTOList;

    /**
     * Validates whether a currency code exists in the given list.
     *
     * @param current The currency code to validate.
     * @throws NotFoundException if the currency code is not found in the list.
     */
    @Override
    public void validate(String current) {
        Optional<String> expectedCurrency = currencyDTOList.stream()
                .filter(code -> Objects.equals(code, current)).findFirst();

        if (expectedCurrency.isEmpty()) {
            throw new NotFoundException("Currency not found.");
        }
    }
}
