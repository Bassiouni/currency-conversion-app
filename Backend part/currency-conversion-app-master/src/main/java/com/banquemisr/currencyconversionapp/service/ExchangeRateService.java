package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.props.AppProps;
import com.banquemisr.currencyconversionapp.validation.AmountValidation;
import com.banquemisr.currencyconversionapp.validation.CurrencyExistsValidation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The ExchangeRateService class is a service that handles exchange rate calculations and validations
 * using an external API and application properties.
 * @author Menna Moataz
 * @author Nouran Hosameldin
 * @author Muhammad Bassiouni
 */
@Service
@EnableConfigurationProperties(value = AppProps.class)
@CacheConfig(cacheNames = "ConCurrency")
/**
 * Constructs an ExchangeRateService with the specified dependencies.
 * @param exchangeRateAPIClient     The ExchangeRateAPIClient used to retrieve exchange rate information.
 * @param appProps                  The application properties containing currency information.
 * @param amountValidation          The validation component for amount values.
 * @param currencyExistsValidation  The validation component for currency existence.
 */
public class ExchangeRateService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;
    private final AppProps appProps;
    private final AmountValidation amountValidation;
    private final CurrencyExistsValidation currencyExistsValidation;

    public ExchangeRateService(
            ExchangeRateAPIClient exchangeRateAPIClient,
            AppProps appProps,
            AmountValidation amountValidation,
            CurrencyExistsValidation currencyExistsValidation
    ) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
        this.appProps = appProps;
        this.amountValidation = amountValidation;

        List<String> codes = appProps.getCurrencies().stream().map(CurrencyDTO::code).toList();

        this.currencyExistsValidation = currencyExistsValidation;
        this.currencyExistsValidation.setCurrencyDTOList(codes);

    }

    /**
     * Retrieves the available currencies from the application properties.
     * @return A Set of CurrencyDTO objects representing the available currencies.
     * @author Muhammad Bassiouni
     */
    @Cacheable
    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.appProps.getCurrencies();
    }

    /**
     * Converts a given currency to another currency.
     * @param current The currency to convert from.
     * @param target  The currency to convert to.
     * @return A UnitCurrencyConversionDTO object representing the conversion rate.
     * @author Muhammad Bassiouni
     */
    @Cacheable
    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    /**
     * Converts a given currency to another currency with a specified amount.
     * @param current The currency to convert from.
     * @param target  The currency to convert to.
     * @param amount  The amount to convert.
     * @return A CurrencyConversionDTO object representing the conversion result.
     * @author Nouran Hosameldin
     */
    @Cacheable
    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        amountValidation.validate(amount);
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

    /**
     * Compares the exchange rates between a base currency and a list of target currencies.
     * @param current The base currency to compare from.
     * @param targets The list of target currencies to compare to.
     * @return A ComparisonDTO object representing the comparison result.
     * @author Menna Moataz
     */
    public ComparisonDTO currencyComparison(String current, List<String> targets) {
        currencyExistsValidation.validate(current);

        targets.forEach(this.currencyExistsValidation::validate);

        ComparisonDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        if (!response.result().equals("success")) {
            return ComparisonDTO
                    .builder()
                    .result("failure")
                    .build();
        }

        Map<String, Double> filteredConversionRates = new HashMap<>();
        Map<String, Double> conversionRates = response.conversionRates();

        targets.forEach(target -> {
            if (conversionRates.containsKey(target)) {
                filteredConversionRates.put(target, conversionRates.get(target));
            }
        });

        return ComparisonDTO
            .builder()
            .result("success")
            .baseCode(response.baseCode())
            .targetCodes(filteredConversionRates.keySet())
            .conversionRates(filteredConversionRates)
            .build();
    }
}
